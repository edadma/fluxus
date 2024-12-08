package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.context.FrameworkConfig
import io.github.edadma.fluxus.core.hooks.EffectHook
import io.github.edadma.fluxus.error.ResourceValidationError
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import org.scalajs.dom

object Reconciler {
  private case class RenderContext(
      rootComponent: Option[ComponentInstance] = None,
      parentNode: Option[FluxusNode] = None,
  )

  def diff(oldNode: Option[FluxusNode], newNode: Option[FluxusNode], container: dom.Element): Unit = {
    val opId    = Logger.nextOperationId
    val context = diffWithContext(oldNode, newNode, container, RenderContext(), opId)

    // Run queued effects after all DOM updates are complete
    context.rootComponent.foreach { instance =>
      Logger.debug(
        Category.StateEffect,
        "Running queued effects after diff",
        opId,
        Map(
          "componentId"   -> instance.id,
          "componentType" -> instance.componentType,
          "effectCount"   -> instance.effects.size,
        ),
      )
      runInitialEffects(instance, opId, false)
      instance.isUpdating = false
    }
  }

  private def diffWithContext(
      oldNode: Option[FluxusNode],
      newNode: Option[FluxusNode],
      container: dom.Element,
      context: RenderContext,
      opId: Int,
  ): RenderContext = {
    Logger.debug(
      Category.VirtualDOM,
      "Starting diff operation",
      opId,
      Map(
        "oldNode"          -> oldNode.map(_.getClass.getSimpleName).getOrElse("None"),
        "newNode"          -> newNode.map(_.getClass.getSimpleName).getOrElse("None"),
        "hasRootComponent" -> context.rootComponent.isDefined,
      ),
    )

    (oldNode, newNode) match {
      case (Some(old), Some(new_)) =>
        (old, new_) match {
          case (oldComp: ComponentNode, newComp: ComponentNode) =>
            (oldComp.instance, newComp.instance) match {
              case (Some(oldInst), Some(newInst)) if oldInst.componentType == newInst.componentType =>
                // Update component if it's the one that triggered the update or is a child of it
                val isUpdating = newInst.isUpdating || context.rootComponent.exists(_.id == newInst.id)

                oldInst.domNode.foreach { element =>
                  if (oldInst.rendered.isDefined && newInst.rendered.isDefined) {
                    runCleanupEffects(oldInst, opId)
                    diffWithContext(
                      oldInst.rendered,
                      newInst.rendered,
                      element.asInstanceOf[dom.Element],
                      if (isUpdating) context.copy(rootComponent = Some(newInst)) else context,
                      opId,
                    )
                    newInst.domNode = Some(element)
                  }
                }
                context

              case _ =>
                replaceNode(oldComp, newComp, container, opId)
                context
            }

          case (oldElem: ElementNode, newElem: ElementNode) if oldElem.tag == newElem.tag =>
            oldElem.domNode.foreach { node =>
              val element = node.asInstanceOf[dom.Element]
              updateAttributes(element, oldElem.props, newElem.props, opId)

              // Maintain context when updating children
              oldElem.children.zip(newElem.children).foreach { case (oldChild, newChild) =>
                diffWithContext(Some(oldChild), Some(newChild), element, context, opId)
              }

              newElem.domNode = Some(element)
            }
            context

          case (oldText: TextNode, newText: TextNode) =>
            oldText.domNode.foreach { node =>
              if (oldText.text != newText.text) {
                node.textContent = newText.text
              }
              newText.domNode = Some(node)
            }
            context

          case _ =>
            replaceNode(old, new_, container, opId)
            context
        }

      case (Some(old), None) =>
        old.domNode.foreach { node =>
          container.removeChild(node)
          old.domNode = None
        }
        context

      case (None, Some(new_)) =>
        val newDom = DOMOperations.createDOMNode(new_)
        container.appendChild(newDom)
        new_ match {
          case ComponentNode(_, _, Some(instance), _) if instance.isUpdating =>
            context.copy(rootComponent = Some(instance))
          case _ =>
            context
        }

      case (None, None) => context
    }
  }

  private def updateAttributes(
      element: dom.Element,
      oldProps: Map[String, Any],
      newProps: Map[String, Any],
      opId: Int,
  ): Unit = {
    Logger.debug(
      Category.VirtualDOM,
      "Updating attributes",
      opId,
      Map(
        "oldProps" -> oldProps,
        "newProps" -> newProps,
      ),
    )

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

  private def replaceNode(
      oldNode: FluxusNode,
      newNode: FluxusNode,
      container: dom.Element,
      opId: Int,
  ): Unit = {
    Logger.debug(
      Category.VirtualDOM,
      "Replacing node",
      opId,
      Map(
        "oldType" -> oldNode.getClass.getSimpleName,
        "newType" -> newNode.getClass.getSimpleName,
      ),
    )

    oldNode.domNode.foreach { oldDom =>
      val newDom = DOMOperations.createDOMNode(newNode)
      container.replaceChild(newDom, oldDom)
      oldNode.domNode = None
    }
  }

  private def runCleanupEffects(instance: ComponentInstance, opId: Int): Unit = {
    Logger.debug(
      Category.StateEffect,
      "Running cleanup effects",
      opId,
      Map(
        "componentId"   -> instance.id,
        "componentType" -> instance.componentType,
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

  private[fluxus] def runInitialEffects(instance: ComponentInstance, opId: Int, isMount: Boolean = false): Unit = {
    if (instance.domNode.isEmpty) {
      Logger.warn(
        Category.StateEffect,
        "No domNode found for component during effects run - assigning from rendered",
        opId,
      )
      instance.rendered.foreach { r =>
        if (r.domNode.isDefined) instance.domNode = r.domNode
      }
    }

    Logger.debug(
      Category.StateEffect,
      if (isMount) "Starting initial mount effect execution" else "Running effects after update",
      opId,
      Map(
        "componentId"   -> instance.id,
        "componentType" -> instance.componentType,
        "effectCount"   -> instance.effects.length,
        "hasRendered"   -> instance.rendered.isDefined,
      ),
    )

    // Run queued effects for this component
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
              ),
            )
            instance.hasEffectError = true
        }
      }
    }

    // Run effects for children
    instance.rendered.foreach { rendered =>
      def runChildEffects(node: FluxusNode): Unit = {
        node match {
          case ComponentNode(_, _, Some(childInstance), _) =>
            runInitialEffects(childInstance, opId, isMount)
          case ElementNode(_, _, _, children, _, _, _, _, _) =>
            children.foreach(runChildEffects)
          case _ => // TextNodes have no children
        }
      }
      runChildEffects(rendered)
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
}
