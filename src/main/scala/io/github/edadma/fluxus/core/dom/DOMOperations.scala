package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import io.github.edadma.fluxus.error.NodeValidationError
import io.github.edadma.fluxus.core.hooks.EffectHook
import org.scalajs.dom

import scala.scalajs.js

object DOMOperations {
  def createDOMNode(vnode: FluxusNode): dom.Node = {
    val opId = Logger.nextOperationId

    Logger.debug(
      Category.VirtualDOM,
      s"Creating DOM node",
      opId,
      Map("nodeType" -> vnode.getClass.getSimpleName),
    )

    val node = vnode match {
      case elem: ElementNode =>
        Logger.debug(
          Category.VirtualDOM,
          s"Creating element",
          opId,
          Map(
            "tag"        -> elem.tag,
            "propsCount" -> elem.props.size,
            "childCount" -> elem.children.size,
          ),
        )

        val element = dom.document.createElement(elem.tag)

        // Set attributes
        for ((name, value) <- elem.props) {
          element.setAttribute(name, value.toString)
        }

        // Set event handlers
        for ((eventName, handler) <- elem.events) {
          val domEventName = eventName.substring(2).toLowerCase
          element.addEventListener(domEventName, handler.asInstanceOf[dom.Event => Unit])
        }

        // Create child nodes
        for (child <- elem.children) {
          val childDom = createDOMNode(child)
          element.appendChild(childDom)
        }

        // Update DOM reference for this element
        elem.domNode = Some(element)
        element

      case textNode: TextNode =>
        val domNode = dom.document.createTextNode(textNode.text)
        textNode.domNode = Some(domNode)
        domNode

      case compNode: ComponentNode =>
        compNode.instance match {
          case Some(inst) =>
            inst.rendered match {
              case Some(rendered) =>
                val domNode = createDOMNode(rendered)
                inst.domNode = Some(domNode)
                domNode
              case None =>
                throw NodeValidationError(
                  "Component has no rendered output",
                  Map("componentType" -> inst.componentType),
                  opId,
                )
            }
          case None =>
            throw NodeValidationError(
              "Component has no instance",
              Map("component" -> compNode.component.getClass.getName),
              opId,
            )
        }
    }

    Logger.debug(
      Category.VirtualDOM,
      s"DOM node creation complete",
      opId,
      Map(
        "nodeType"    -> node.nodeName,
        "hasParent"   -> (node.parentNode != null),
        "vNodeHasDom" -> vnode.domNode.isDefined,
      ),
    )

    node
  }

  def mount(node: FluxusNode, container: dom.Element): dom.Node = {
    val opId = Logger.nextOperationId

    Logger.info(
      Category.VirtualDOM,
      "Starting app mount",
      opId,
      Map(
        "containerId"       -> container.id,
        "containerChildren" -> container.childNodes.length,
      ),
    )

    // Clean up any existing component before mounting new one
    if (container.hasChildNodes()) {
      Logger.debug(Category.VirtualDOM, "Clearing container", opId)

      // Find and cleanup any existing component
      val maybeExistingComponent = node match {
        case ComponentNode(_, _, Some(newInstance), _) =>
          // Look for existing component instance
          Option(container.firstChild).flatMap { firstChild =>
            container.childNodes.collectFirst {
              case node if node.hasOwnProperty("__fluxusInstance") =>
                node.asInstanceOf[js.Dynamic].__fluxusInstance.asInstanceOf[ComponentInstance]
            }
          }
        case _ => None
      }

      // Run cleanup on existing component if found
      maybeExistingComponent.foreach { oldInstance =>
        Logger.debug(
          Category.StateEffect,
          "Running cleanup for existing component",
          opId,
          Map("componentId" -> oldInstance.id),
        )
        oldInstance.hooks.foreach {
          case hook: EffectHook =>
            hook.cleanup.foreach { cleanup =>
              try {
                Logger.debug(Category.StateEffect, "Running effect cleanup", opId)
                cleanup()
              } catch {
                case error: Throwable =>
                  Logger.error(
                    Category.StateEffect,
                    "Effect cleanup failed",
                    opId,
                    Map("error" -> error.getMessage),
                  )
              }
            }
          case _ => // Not an effect hook
        }
      }

      // Clear container
      while (container.firstChild != null) {
        container.removeChild(container.firstChild)
      }
    }

    Logger.debug(Category.VirtualDOM, "Creating root DOM node", opId)
    val domNode = createDOMNode(node)

    // Store component instance reference on DOM node if this is a component
    node match {
      case ComponentNode(_, _, Some(instance), _) =>
        domNode.asInstanceOf[js.Dynamic].__fluxusInstance = instance.asInstanceOf[js.Any]
      case _ =>
    }

    Logger.debug(Category.VirtualDOM, "Appending to container", opId)
    container.appendChild(domNode)

    // Run initial effects for new component
    node match {
      case ComponentNode(_, _, Some(instance), _) =>
        runInitialEffects(instance, opId)
      case _ =>
    }

    Logger.info(
      Category.VirtualDOM,
      "App mount complete",
      opId,
      Map(
        "rootNodeType" -> domNode.nodeName,
        "rootHasDom"   -> node.domNode.isDefined,
      ),
    )

    domNode
  }

  private def runCleanupEffects(instance: ComponentInstance, opId: Int): Unit = {
    Logger.debug(
      Category.StateEffect,
      "Running cleanup effects",
      opId,
      Map(
        "componentId"   -> instance.id,
        "componentType" -> instance.componentType,
        "hookCount"     -> instance.hooks.length,
      ),
    )

    instance.hooks.foreach {
      case hook: EffectHook =>
        hook.cleanup.foreach { cleanup =>
          try {
            cleanup()
            Logger.debug(Category.StateEffect, "Effect cleanup complete", opId)
          } catch {
            case error: Throwable =>
              Logger.error(
                Category.StateEffect,
                "Effect cleanup failed",
                opId,
                Map("error" -> error.getMessage),
              )
          }
        }
      case _ => // Not an effect hook
    }
  }

  private def runInitialEffects(instance: ComponentInstance, opId: Int): Unit = {
    Logger.debug(
      Category.StateEffect,
      "Running initial effects",
      opId,
      Map(
        "componentId" -> instance.id,
        "effectCount" -> instance.effects.length,
      ),
    )

    val effects = instance.effects
    instance.effects = Vector.empty // Clear queue before running
    effects.foreach { effect =>
      try {
        effect()
      } catch {
        case error: Throwable =>
          Logger.error(
            Category.StateEffect,
            "Effect execution failed",
            opId,
            Map(
              "error"       -> error.getMessage,
              "componentId" -> instance.id,
            ),
          )
          instance.hasEffectError = true
      }
    }
  }

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
        // Run cleanup before removing node
        old match {
          case ComponentNode(_, _, Some(instance), _) =>
            runCleanupEffects(instance, opId)
          case _ =>
        }

        Logger.debug(Category.VirtualDOM, "Removing node", opId)
        old.domNode.foreach { node =>
          container.removeChild(node)
          old.domNode = None // Clear the reference
        }

      case (None, Some(new_)) =>
        Logger.debug(Category.VirtualDOM, "Adding new node", opId)
        val newDomNode = createDOMNode(new_)
        container.appendChild(newDomNode)

        // Run effects for new component
        new_ match {
          case ComponentNode(_, _, Some(instance), _) =>
            runInitialEffects(instance, opId)
          case _ =>
        }

      case (Some(old), Some(new_)) if old.key != new_.key =>
        Logger.debug(Category.VirtualDOM, "Different keys - replacing node", opId)

        // Run cleanup on old component
        old match {
          case ComponentNode(_, _, Some(instance), _) =>
            runCleanupEffects(instance, opId)
          case _ =>
        }

        old.domNode.foreach { oldDom =>
          val newDom = createDOMNode(new_)
          container.replaceChild(newDom, oldDom)
          old.domNode = None // Clear old reference
        }

        // Run effects for new component
        new_ match {
          case ComponentNode(_, _, Some(instance), _) =>
            runInitialEffects(instance, opId)
          case _ =>
        }

      case (Some(old), Some(new_)) => diffNodes(old, new_, opId)

      case (None, None) =>
        Logger.debug(Category.VirtualDOM, "No nodes to diff", opId)
    }
  }

  private def diffNodes(old: FluxusNode, new_ : FluxusNode, opId: Int): Unit = {
    (old, new_) match {
      case (oldText: TextNode, newText: TextNode) =>
        oldText.domNode.foreach { textNode =>
          if (oldText.text != newText.text) {
            textNode.textContent = newText.text
          }
          newText.domNode = Some(textNode)
        }

      case (oldElem: ElementNode, newElem: ElementNode) =>
        Logger.debug(Category.VirtualDOM, "Comparing element nodes", opId)
        diffElements(oldElem, newElem, opId)

      case (oldComp: ComponentNode, newComp: ComponentNode) =>
        Logger.debug(Category.VirtualDOM, "Comparing component nodes", opId)
        diffComponents(oldComp, newComp, opId)

      case _ =>
        old.domNode.foreach { oldDom =>
          val newDom = createDOMNode(new_)
          oldDom.parentNode.replaceChild(newDom, oldDom)
        }
    }
  }

  private def diffElements(oldNode: ElementNode, newNode: ElementNode, opId: Int): Unit = {
    if (oldNode.tag != newNode.tag) {
      oldNode.domNode.foreach { domNode =>
        val newDom = createDOMNode(newNode)
        domNode.parentNode.replaceChild(newDom, domNode)
      }
      return
    }

    oldNode.domNode.foreach { element =>
      // Update attributes
      val domElement = element.asInstanceOf[dom.Element]
      updateAttributes(domElement, oldNode.props, newNode.props, opId)

      // Update children
      val oldChildren = oldNode.children
      val newChildren = newNode.children

      // Handle children updates
      var i = 0
      while (i < Math.max(oldChildren.length, newChildren.length)) {
        val oldChild = oldChildren.lift(i)
        val newChild = newChildren.lift(i)

        diff(oldChild, newChild, domElement)
        i += 1
      }

      // Pass DOM reference to new node
      newNode.domNode = Some(element)
    }
  }

  private def updateAttributes(
      element: dom.Element,
      oldProps: Map[String, Any],
      newProps: Map[String, Any],
      opId: Int,
  ): Unit = {
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
  }

  private def diffComponents(
      oldNode: ComponentNode,
      newNode: ComponentNode,
      opId: Int,
  ): Unit = {
    (oldNode.instance, newNode.instance) match {
      case (Some(oldInst), Some(newInst)) =>
        if (oldInst.componentType != newInst.componentType) {
          // Run cleanup on old component
          runCleanupEffects(oldInst, opId)

          oldInst.domNode.foreach { oldDom =>
            val newDom = createDOMNode(newNode)
            oldDom.parentNode.replaceChild(newDom, oldDom)
            oldInst.domNode = None
          }

          // Run effects for new component
          runInitialEffects(newInst, opId)
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
}
