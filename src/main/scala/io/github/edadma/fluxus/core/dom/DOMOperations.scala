package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import io.github.edadma.fluxus.error.NodeValidationError
import org.scalajs.dom
import io.github.edadma.fluxus.core.dom.DOMExtensions._
import io.github.edadma.fluxus.core.hooks.EffectHook

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

    if (container.hasChildNodes()) {
      Logger.debug(Category.VirtualDOM, "Clearing container", opId)
      while (container.firstChild != null) {
        container.removeChild(container.firstChild)
      }
    }

    Logger.debug(Category.VirtualDOM, "Creating root DOM node", opId)
    val domNode = createDOMNode(node)

    Logger.debug(Category.VirtualDOM, "Appending to container", opId)
    container.appendChild(domNode)

    // Run effects after DOM operations are complete
    node match {
      case ComponentNode(_, _, Some(instance), _) =>
        Logger.debug(
          Category.StateEffect,
          "Running component effects after mount",
          opId,
          Map(
            "componentId" -> instance.id,
            "effectCount" -> instance.effects.length,
          ),
        )

        // Execute queued effects
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

      case _ => // Not a component node, no effects to run
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
}
