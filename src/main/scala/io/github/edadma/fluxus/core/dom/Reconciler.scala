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
  def diff(oldNode: Option[FluxusNode], newNode: Option[FluxusNode], container: DOMElement): Unit = {
    val opId      = Logger.nextOperationId
    val startTime = System.currentTimeMillis()
    val config    = FrameworkConfig.current

    Logger.debug(
      Category.VirtualDOM,
      "Starting diff operation",
      opId,
      Map(
        "oldNode" -> oldNode.map(_.getClass.getSimpleName).getOrElse("None"),
        "newNode" -> newNode.map(_.getClass.getSimpleName).getOrElse("None"),
      ),
    )

    try {
      (oldNode, newNode) match {
        case (Some(old), None) =>
          Logger.debug(Category.VirtualDOM, "Removing node", opId)
          removeNode(old, container, opId)
        case (None, Some(new_)) =>
          Logger.debug(Category.VirtualDOM, "Adding new node", opId)
          val newDomNode = DOMOperations.createDOMNode(new_)
          container.appendChild(newDomNode)
        case (Some(old), Some(new_)) if old.key != new_.key =>
          Logger.debug(Category.VirtualDOM, "Different keys - replacing node", opId)
          replaceNode(old, new_, container, opId)
        case (Some(old), Some(new_)) => (old, new_) match {
            case (oldText: TextNode, newText: TextNode) =>
              Logger.debug(
                Category.VirtualDOM,
                "Comparing text nodes",
                opId,
                Map(
                  "oldText"     -> oldText.text,
                  "newText"     -> newText.text,
                  "isDifferent" -> (oldText.text != newText.text),
                  "hasDomNode"  -> oldText.domNode.isDefined,
                ),
              )
              if (oldText.text != newText.text) {
                oldText.domNode.foreach(_.textContent = newText.text)
              }
            case (oldElem: ElementNode, newElem: ElementNode) =>
              Logger.debug(Category.VirtualDOM, "Comparing element nodes", opId)
              diffElements(oldElem, newElem, opId)
            case (oldComp: ComponentNode, newComp: ComponentNode) =>
              Logger.debug(Category.VirtualDOM, "Comparing component nodes", opId)
              diffComponents(oldComp, newComp, opId)
            case _ =>
              if (old.getClass != new_.getClass) {
                Logger.debug(Category.VirtualDOM, "Different types - replacing node", opId)
                replaceNode(old, new_, container, opId)
              }
          }
        case (None, None) =>
          Logger.debug(Category.VirtualDOM, "No nodes to diff", opId)
      }

      val duration = System.currentTimeMillis() - startTime
      if (duration > config.renderTimeout) {
        Logger.warn(
          Category.Render,
          "Render timeout exceeded",
          opId,
          Map(
            "duration" -> duration,
            "timeout"  -> config.renderTimeout,
          ),
        )
      }
    } catch {
      case e: Throwable =>
        Logger.error(
          Category.VirtualDOM,
          "Error during diff operation",
          opId,
          Map("error" -> e.getMessage),
        )
        throw e
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
    // Remove old props
    oldProps.keys.foreach { name =>
      if (!newProps.contains(name)) {
        Logger.trace(Category.VirtualDOM, s"Removing attribute: $name", opId)
        element.removeAttribute(name)
      }
    }

    // Set new/changed props
    newProps.foreach { case (name, value) =>
      if (!oldProps.get(name).contains(value)) {
        Logger.trace(
          Category.VirtualDOM,
          "Setting attribute",
          opId,
          Map("name" -> name, "value" -> value),
        )
        element.setAttribute(name, value.toString)
      }
    }
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
        // Check tree depth before proceeding
        checkTreeDepth(newInst, opId)

        val startTime = System.currentTimeMillis()
        if (oldInst.componentType != newInst.componentType) {
          // Different component types - full replace
          Logger.debug(
            Category.VirtualDOM,
            "Different component types",
            opId,
            Map(
              "oldType" -> oldInst.componentType,
              "newType" -> newInst.componentType,
            ),
          )
          oldInst.rendered.zip(oldNode.domNode).foreach { case (oldRendered, container) =>
            removeNode(oldRendered, container.asInstanceOf[DOMElement], opId)
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
            Logger.debug(
              Category.VirtualDOM,
              "Updating component",
              opId,
              Map("componentType" -> oldInst.componentType),
            )

            // Create updated instance with new props
            val updatedInst = oldInst.copy(props = newInst.props)
            updatedInst.needsRender = true

            // Re-render and track timing
            oldInst.rendered.zip(newInst.rendered).foreach { case (oldRendered, newRendered) =>
              oldNode.domNode.foreach { container =>
                diff(Some(oldRendered), Some(newRendered), container.asInstanceOf[DOMElement])
              }
            }

            val duration = System.currentTimeMillis() - startTime
            updatedInst.lastRenderDuration = duration
            updatedInst.totalRenderTime += duration
            updatedInst.updateCount += 1
            updatedInst.lastUpdateTime = System.currentTimeMillis()

            // Record debug information if enabled
            if (FrameworkConfig.current.debugMode) {
              DebugTools.recordRender(
                updatedInst.id,
                RenderInfo(
                  componentType = updatedInst.componentType,
                  props = updatedInst.props,
                  state = updatedInst.hooks,
                  duration = duration,
                  depth = updatedInst.depth,
                ),
                opId,
              )
            }
          }
        }
      case _ =>
        Logger.error(
          Category.VirtualDOM,
          "Invalid component instance state",
          opId,
          Map(
            "oldHasInstance" -> oldNode.instance.isDefined,
            "newHasInstance" -> newNode.instance.isDefined,
          ),
        )
    }
  }
}
