package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.core.context.FrameworkConfig
import io.github.edadma.fluxus.core.debug.{DebugTools, RenderInfo}
import io.github.edadma.fluxus.error.ResourceValidationError
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import org.scalajs.dom
import org.scalajs.dom.{Element => DOMElement, Text => DOMText, Node => DOMNode}

object Reconciler {
  def diff(oldNode: Option[FluxusNode], newNode: Option[FluxusNode], container: dom.Element): Unit = {
    val opId = Logger.nextOperationId

    Logger.debug(
      Category.VirtualDOM,
      "Starting diff operation",
      opId,
      Map(
        "oldNode" -> oldNode.map(_.getClass.getSimpleName).getOrElse("None"),
        "newNode" -> newNode.map(_.getClass.getSimpleName).getOrElse("None"),
      ),
    )

    (oldNode, newNode) match {
      case (Some(old), None) =>
        Logger.debug(Category.VirtualDOM, "Removing node", opId)
        old.domNode.foreach { node =>
          container.removeChild(node)
          old.domNode = None // Clear the reference
        }

      case (None, Some(new_)) =>
        Logger.debug(Category.VirtualDOM, "Adding new node", opId)
        val newDomNode = DOMOperations.createDOMNode(new_)
        container.appendChild(newDomNode)

      case (Some(old), Some(new_)) if old.key != new_.key =>
        Logger.debug(Category.VirtualDOM, "Different keys - replacing node", opId)
        old.domNode.foreach { oldDom =>
          val newDom = DOMOperations.createDOMNode(new_)
          container.replaceChild(newDom, oldDom)
          old.domNode = None // Clear old reference
        }

      case (Some(old), Some(new_)) => (old, new_) match {
          case (oldText: TextNode, newText: TextNode) =>
            oldText.domNode.foreach { textNode =>
              if (oldText.text != newText.text) {
                textNode.textContent = newText.text
              }
              newText.domNode = Some(textNode) // Pass the DOM reference to the new node
            }

          case (oldElem: ElementNode, newElem: ElementNode) =>
            Logger.debug(Category.VirtualDOM, "Comparing element nodes", opId)
            diffElements(oldElem, newElem, container, opId)

          case (oldComp: ComponentNode, newComp: ComponentNode) =>
            Logger.debug(Category.VirtualDOM, "Comparing component nodes", opId)
            diffComponents(oldComp, newComp, container, opId)

          case _ =>
            if (old.getClass != new_.getClass) {
              old.domNode.foreach { oldDom =>
                val newDom = DOMOperations.createDOMNode(new_)
                container.replaceChild(newDom, oldDom)
                old.domNode = None // Clear old reference
              }
            }
        }

      case (None, None) =>
        Logger.debug(Category.VirtualDOM, "No nodes to diff", opId)
    }
  }

  private def diffElements(oldNode: ElementNode, newNode: ElementNode, container: dom.Element, opId: Int): Unit = {
    Logger.debug(
      Category.VirtualDOM,
      "DiffElements details",
      opId,
      Map(
        "oldNodeHasDomNode" -> oldNode.domNode.isDefined,
        "oldNodeChildren"   -> oldNode.children.length,
        "newNodeChildren"   -> newNode.children.length,
        "oldNodeTag"        -> oldNode.tag,
        "newNodeTag"        -> newNode.tag,
        "oldNodeProps"      -> oldNode.props,
        "newNodeProps"      -> newNode.props,
      ),
    )

    if (oldNode.tag != newNode.tag) {
      oldNode.domNode.foreach { domNode =>
        val newDom = DOMOperations.createDOMNode(newNode)
        container.replaceChild(newDom, domNode)
        oldNode.domNode = None
      }
      return
    }

    oldNode.domNode.foreach { domNode =>
      // Cast to Element since we know this is an element node
      val element = domNode.asInstanceOf[dom.Element]

      // Update attributes
      val oldProps = oldNode.props
      val newProps = newNode.props

      // Remove old props
      oldProps.keys.foreach { name =>
        if (!newProps.contains(name)) {
          element.removeAttribute(name)
        }
      }

      // Set new/changed props
      newProps.foreach { case (name, value) =>
        val newValue = value.toString
        if (oldProps.get(name).map(_.toString) != Some(newValue)) {
          element.setAttribute(name, newValue)
        }
      }

      // Update children
      val oldChildren = oldNode.children
      val newChildren = newNode.children

      // Handle children updates
      var i = 0
      while (i < Math.max(oldChildren.length, newChildren.length)) {
        val oldChild = oldChildren.lift(i)
        val newChild = newChildren.lift(i)

        diff(oldChild, newChild, element)
        i += 1
      }

      // Pass DOM reference to new node
      newNode.domNode = Some(element)
    }
  }

  private def diffComponents(
      oldNode: ComponentNode,
      newNode: ComponentNode,
      container: dom.Element,
      opId: Int,
  ): Unit = {
    (oldNode.instance, newNode.instance) match {
      case (Some(oldInst), Some(newInst)) =>
        if (oldInst.componentType != newInst.componentType) {
          oldInst.domNode.foreach { oldDom =>
            val newDom = DOMOperations.createDOMNode(newNode)
            container.replaceChild(newDom, oldDom)
            oldInst.domNode = None
          }
        } else {
          // Diff the rendered output
          val oldRendered = oldInst.rendered
          val newRendered = newInst.rendered

          oldInst.domNode.foreach { element =>
            diff(oldRendered, newRendered, element.asInstanceOf[dom.Element])
            // Pass DOM reference to new instance
            newInst.domNode = Some(element)
          }
        }
      case _ =>
        Logger.error(Category.VirtualDOM, "Invalid component instance state", opId)
    }
  }

  private def checkTreeDepth(instance: ComponentInstance, opId: Int): Unit = {
    val config = FrameworkConfig.current
    if (instance.depth > config.maxTreeDepth) {
      Logger.error(
        Category.Memory,
        "Maximum tree depth exceeded",
        opId,
        Map(
          "componentType" -> instance.componentType,
          "depth"         -> instance.depth,
          "maxDepth"      -> config.maxTreeDepth,
        ),
      )
      throw ResourceValidationError(
        s"Maximum tree depth of ${config.maxTreeDepth} exceeded",
        Map("actualDepth" -> instance.depth),
        opId,
      )
    }
  }

  private def removeNode(node: FluxusNode, container: DOMElement, opId: Int): Unit = {
    node.domNode.foreach { domNode =>
      Logger.debug(
        Category.VirtualDOM,
        "Removing DOM node",
        opId,
        Map("nodeType" -> node.getClass.getSimpleName),
      )
      container.removeChild(domNode)
    }
  }

  private def replaceNode(oldNode: FluxusNode, newNode: FluxusNode, container: DOMElement, opId: Int): Unit = {
    oldNode.domNode.foreach { oldDom =>
      Logger.debug(
        Category.VirtualDOM,
        "Replacing node",
        opId,
        Map(
          "oldType" -> oldNode.getClass.getSimpleName,
          "newType" -> newNode.getClass.getSimpleName,
        ),
      )
      val newDom = DOMOperations.createDOMNode(newNode)
      container.replaceChild(newDom, oldDom)
    }
  }

  private def diffElements(oldNode: ElementNode, newNode: ElementNode, opId: Int): Unit = {
    Logger.debug(
      Category.VirtualDOM,
      "DiffElements details",
      opId,
      Map(
        "oldNodeTag"        -> oldNode.tag,
        "newNodeTag"        -> newNode.tag,
        "oldNodeProps"      -> oldNode.props,
        "newNodeProps"      -> newNode.props,
        "oldNodeHasDomNode" -> oldNode.domNode.isDefined,
        "oldNodeChildren"   -> oldNode.children.length,
        "newNodeChildren"   -> newNode.children.length,
      ),
    )

    if (oldNode.tag != newNode.tag) {
      Logger.debug(
        Category.VirtualDOM,
        "Different tags - need full replace",
        opId,
        Map(
          "oldTag" -> oldNode.tag,
          "newTag" -> newNode.tag,
        ),
      )
      oldNode.domNode.foreach { domNode =>
        val parent = domNode.parentNode
        val newDom = DOMOperations.createDOMNode(newNode)
        parent.replaceChild(newDom, domNode)
      }
      return
    }

    oldNode.domNode.foreach { element =>
      Logger.debug(
        Category.VirtualDOM,
        "Found DOM node for diff",
        opId,
        Map(
          "elementType" -> element.nodeName,
          "elementId"   -> element.asInstanceOf[dom.Element].id,
        ),
      )

      val domElement = element.asInstanceOf[DOMElement]

      // Update attributes
      updateAttributes(domElement, oldNode.props, newNode.props, opId)

      // Update children
      updateChildren(oldNode, newNode, domElement, opId)
    }
  }

  private def updateAttributes(
      element: DOMElement,
      oldProps: Map[String, Any],
      newProps: Map[String, Any],
      opId: Int,
  ): Unit = {
    Logger.debug(
      Category.VirtualDOM,
      "Updating attributes",
      opId,
      Map(
        "oldProps"         -> oldProps,
        "newProps"         -> newProps,
        "element"          -> element.nodeName,
        "currentClassName" -> element.getAttribute("class"),
      ),
    )

    // Remove old props
    oldProps.keys.foreach { name =>
      if (!newProps.contains(name)) {
        Logger.debug(
          Category.VirtualDOM,
          "Removing attribute",
          opId,
          Map(
            "name"     -> name,
            "oldValue" -> oldProps(name),
          ),
        )
        element.removeAttribute(name)
      }
    }

    // Set new/changed props
    newProps.foreach { case (name, value) =>
      val oldValue = oldProps.get(name)
      val needsUpdate = oldValue match {
        case Some(old) =>
          val oldStr = old.toString
          val newStr = value.toString
          if (oldStr != newStr) {
            Logger.debug(
              Category.VirtualDOM,
              "Attribute value changed",
              opId,
              Map(
                "attribute" -> name,
                "oldValue"  -> oldStr,
                "newValue"  -> newStr,
              ),
            )
            true
          } else false
        case None =>
          Logger.debug(
            Category.VirtualDOM,
            "New attribute found",
            opId,
            Map(
              "attribute" -> name,
              "value"     -> value,
            ),
          )
          true
      }

      if (needsUpdate) {
        Logger.debug(
          Category.VirtualDOM,
          "Setting attribute",
          opId,
          Map(
            "name"          -> name,
            "value"         -> value,
            "elementBefore" -> element.getAttribute(name),
          ),
        )
        element.setAttribute(name, value.toString)
        Logger.debug(
          Category.VirtualDOM,
          "Attribute set complete",
          opId,
          Map(
            "name"         -> name,
            "elementAfter" -> element.getAttribute(name),
          ),
        )
      }
    }

    Logger.debug(
      Category.VirtualDOM,
      "Attributes update complete",
      opId,
      Map(
        "finalClassName" -> element.getAttribute("class"),
      ),
    )
  }

  private def updateChildren(oldNode: ElementNode, newNode: ElementNode, container: DOMElement, opId: Int): Unit = {
    val oldChildren = oldNode.children
    val newChildren = newNode.children

    // Handle keyed children first
    val oldKeyed = oldChildren.filter(_.key.isDefined).map(n => (n.key.get, n)).toMap
    val newKeyed = newChildren.filter(_.key.isDefined).map(n => (n.key.get, n)).toMap

    // Update/remove/add keyed children
    var lastKeyedIndex = -1
    newKeyed.foreach { case (key, newChild) =>
      oldKeyed.get(key) match {
        case Some(oldChild) =>
          // Update existing keyed child
          diff(Some(oldChild), Some(newChild), container)
          lastKeyedIndex = oldChildren.indexWhere(_.key.contains(key))
        case None =>
          // Add new keyed child
          val newDom = DOMOperations.createDOMNode(newChild)
          if (lastKeyedIndex + 1 < oldChildren.length) {
            oldChildren(lastKeyedIndex + 1).domNode.foreach { nextDom =>
              container.insertBefore(newDom, nextDom)
            }
          } else {
            container.appendChild(newDom)
          }
      }
    }

    // Remove old keyed children that aren't in new set
    oldKeyed.keys.foreach { key =>
      if (!newKeyed.contains(key)) {
        oldKeyed(key).domNode.foreach(container.removeChild)
      }
    }

    // Handle non-keyed children
    val oldNonKeyed = oldChildren.filter(_.key.isEmpty)
    val newNonKeyed = newChildren.filter(_.key.isEmpty)

    // Update existing non-keyed children
    oldNonKeyed.zip(newNonKeyed).foreach { case (oldChild, newChild) =>
      diff(Some(oldChild), Some(newChild), container)
    }

    // Remove extra old children
    oldNonKeyed.drop(newNonKeyed.length).foreach { oldChild =>
      oldChild.domNode.foreach(container.removeChild)
    }

    // Add new children
    newNonKeyed.drop(oldNonKeyed.length).foreach { newChild =>
      container.appendChild(DOMOperations.createDOMNode(newChild))
    }
  }

  private def diffComponents(oldNode: ComponentNode, newNode: ComponentNode, opId: Int): Unit = {
    (oldNode.instance, newNode.instance) match {
      case (Some(oldInst), Some(newInst)) =>
        val startTime = System.currentTimeMillis()

        if (oldInst.componentType != newInst.componentType) {
          // Different component types - full replace
          Logger.debug(Category.VirtualDOM, "Different component types", opId)
          oldInst.rendered.zip(oldNode.domNode).foreach { case (oldRendered, container) =>
            removeNode(oldRendered, container.asInstanceOf[dom.Element], opId)
          }
          newInst.rendered.foreach { newRendered =>
            oldNode.domNode.foreach { container =>
              container.appendChild(DOMOperations.createDOMNode(newRendered))
            }
          }
        } else {
          // Same component type - maybe update
          val shouldUpdate = oldInst.props != newInst.props || oldInst.needsRender
          if (shouldUpdate) {
            Logger.debug(Category.VirtualDOM, "Updating component", opId)

            // Create updated instance with new props
            val updatedInst = oldInst.copy(props = newInst.props)
            updatedInst.needsRender = true

            // Re-render and track timing
            oldInst.rendered.zip(newInst.rendered).foreach { case (oldRendered, newRendered) =>
              oldNode.domNode.foreach { container =>
                diff(Some(oldRendered), Some(newRendered), container.asInstanceOf[dom.Element])
              }
            }

            val duration = System.currentTimeMillis() - startTime
            updatedInst.lastRenderDuration = duration
            updatedInst.totalRenderTime += duration
            updatedInst.updateCount += 1
            updatedInst.lastUpdateTime = System.currentTimeMillis()
          }
        }
      case _ =>
        Logger.error(Category.VirtualDOM, "Invalid component instance state", opId)
    }
  }
}
