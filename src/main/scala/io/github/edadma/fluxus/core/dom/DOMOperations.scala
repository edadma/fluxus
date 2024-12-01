package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import io.github.edadma.fluxus.error.NodeValidationError
import org.scalajs.dom
import org.scalajs.dom.{Element => DOMElement, Text => DOMText}

object DOMOperations {
  def createDOMNode(vnode: FluxusNode): dom.Node = {
    val opId = Logger.nextOperationId

    Logger.debug(
      Category.VirtualDOM,
      s"Creating DOM node",
      opId,
      Map(
        "nodeType" -> vnode.getClass.getSimpleName,
      ),
    )

    val node = vnode match {
      case ElementNode(tag, props, events, children, _, _, _, namespace, _) =>
        Logger.debug(
          Category.VirtualDOM,
          s"Creating element",
          opId,
          Map(
            "tag"        -> tag,
            "propsCount" -> props.size,
            "childCount" -> children.size,
          ),
        )

        val element = dom.document.createElement(tag)

        for ((name, value) <- props) {
          Logger.trace(
            Category.VirtualDOM,
            s"Setting attribute",
            opId,
            Map(
              "element"   -> tag,
              "attribute" -> name,
              "value"     -> value,
            ),
          )
          element.setAttribute(name, value.toString)
        }

        children.zipWithIndex.foreach { case (child, index) =>
          Logger.trace(
            Category.VirtualDOM,
            s"Creating child",
            opId,
            Map(
              "parent"     -> tag,
              "childIndex" -> index,
              "childType"  -> child.getClass.getSimpleName,
            ),
          )
          element.appendChild(createDOMNode(child))
        }

        element

      case TextNode(text, _, _, _) =>
        Logger.debug(
          Category.VirtualDOM,
          s"Creating text node",
          opId,
          Map(
            "textLength"  -> text.length,
            "textPreview" -> text.take(20),
          ),
        )
        dom.document.createTextNode(text)

      case ComponentNode(component, props, instance: Option[ComponentInstance] @unchecked, _) =>
        Logger.debug(
          Category.VirtualDOM,
          s"Processing component node",
          opId,
          Map(
            "componentType" -> component.getClass.getName,
          ),
        )

        instance match {
          case Some(inst) =>
            inst.rendered match {
              case Some(rendered) =>
                Logger.debug(
                  Category.VirtualDOM,
                  s"Rendering component output",
                  opId,
                  Map(
                    "componentType" -> inst.componentType,
                    "outputType"    -> rendered.getClass.getSimpleName,
                  ),
                )
                createDOMNode(rendered)
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
              Map("component" -> component.getClass.getName),
              opId,
            )
        }
    }

    Logger.debug(
      Category.VirtualDOM,
      s"DOM node creation complete",
      opId,
      Map(
        "nodeType" -> node.nodeName,
      ),
    )

    node
  }

  def mount(vnode: FluxusNode, container: DOMElement): Unit = {
    val opId = Logger.nextOperationId

    Logger.info(
      Category.VirtualDOM,
      s"Starting app mount",
      opId,
      Map(
        "containerId"       -> container.id,
        "containerChildren" -> container.childNodes.length,
      ),
    )

    if (container.hasChildNodes()) {
      Logger.debug(
        Category.VirtualDOM,
        s"Clearing container",
        opId,
        Map(
          "childCount" -> container.childNodes.length,
        ),
      )
      while (container.firstChild != null) {
        container.removeChild(container.firstChild)
      }
    }

    Logger.debug(Category.VirtualDOM, s"Creating root DOM node", opId)
    val domNode = createDOMNode(vnode)

    Logger.debug(Category.VirtualDOM, s"Appending to container", opId)
    container.appendChild(domNode)

    Logger.info(
      Category.VirtualDOM,
      s"App mount complete",
      opId,
      Map(
        "rootNodeType" -> domNode.nodeName,
      ),
    )
  }
}
