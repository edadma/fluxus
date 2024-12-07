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

  private def runInitialEffects(instance: ComponentInstance, opId: Int): Unit = {
    Logger.debug(
      Category.StateEffect,
      "Starting effect execution",
      opId,
      Map(
        "componentId"   -> instance.id,
        "componentType" -> instance.componentType,
        "effectCount"   -> instance.effects.length,
        "hasRendered"   -> instance.rendered.isDefined,
      ),
    )

    // Run this component's effects
    if (instance.effects.nonEmpty) {
      val effects = instance.effects
      instance.effects = Vector.empty // Clear queue before running

      effects.zipWithIndex.foreach { case (effect, idx) =>
        try {
          Logger.debug(
            Category.StateEffect,
            s"Running effect ${idx + 1}/${effects.length}",
            opId,
            Map(
              "componentId"   -> instance.id,
              "componentType" -> instance.componentType,
            ),
          )
          effect()
        } catch {
          case error: Throwable =>
            Logger.error(
              Category.StateEffect,
              "Effect execution failed",
              opId,
              Map(
                "componentId" -> instance.id,
                "effectIndex" -> idx,
                "error"       -> error.getMessage,
                "errorType"   -> error.getClass.getName,
              ),
            )
            instance.hasEffectError = true
        }
      }
    }

    // Find and run effects for child components
    instance.rendered.foreach { rendered =>
      def runChildEffects(node: FluxusNode): Unit = {
        node match {
          case ComponentNode(_, _, Some(childInstance), _) =>
            Logger.debug(
              Category.StateEffect,
              "Found child component",
              opId,
              Map(
                "parentId"         -> instance.id,
                "childId"          -> childInstance.id,
                "childType"        -> childInstance.componentType,
                "childEffectCount" -> childInstance.effects.length,
              ),
            )
            runInitialEffects(childInstance, opId)

          case ElementNode(_, _, _, children, _, _, _, _, _) =>
            children.foreach(runChildEffects)

          case _ => // TextNodes have no children
        }
      }

      Logger.debug(
        Category.StateEffect,
        "Starting child component search",
        opId,
        Map(
          "parentId"   -> instance.id,
          "parentType" -> instance.componentType,
        ),
      )

      runChildEffects(rendered)
    }

    Logger.debug(
      Category.StateEffect,
      "Effect execution complete",
      opId,
      Map(
        "componentId"   -> instance.id,
        "componentType" -> instance.componentType,
      ),
    )
  }
}
