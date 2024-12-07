package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.context.FrameworkConfig
import io.github.edadma.fluxus.core.hooks.EffectHook
import io.github.edadma.fluxus.error.ResourceValidationError
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import org.scalajs.dom

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
        // Run cleanup before removing node
        old match {
          case ComponentNode(_, _, Some(instance), _) =>
            runCleanupEffects(instance, opId)
          case _ =>
        }

        Logger.debug(Category.VirtualDOM, "Removing node", opId)
        old.domNode.foreach { node =>
          container.removeChild(node)
          old.domNode = None
        }

      case (None, Some(new_)) =>
        Logger.debug(Category.VirtualDOM, "Adding new node", opId)
        val newDomNode = DOMOperations.createDOMNode(new_)
        container.appendChild(newDomNode)

        // Run initial effects for new component
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
          val newDom = DOMOperations.createDOMNode(new_)
          container.replaceChild(newDom, oldDom)
          old.domNode = None
        }

        // Run initial effects for new component
        new_ match {
          case ComponentNode(_, _, Some(instance), _) =>
            runInitialEffects(instance, opId)
          case _ =>
        }

      case (Some(old), Some(new_)) =>
        (old, new_) match {
          case (oldText: TextNode, newText: TextNode) =>
            oldText.domNode.foreach { textNode =>
              if (oldText.text != newText.text) {
                textNode.textContent = newText.text
              }
              newText.domNode = Some(textNode)
            }

          case (oldElem: ElementNode, newElem: ElementNode) =>
            if (oldElem.tag != newElem.tag) {
              replaceNode(oldElem, newElem, container, opId)
            } else {
              oldElem.domNode.foreach { domNode =>
                val element = domNode.asInstanceOf[dom.Element]
                updateAttributes(element, oldElem.props, newElem.props, opId)
                updateChildren(oldElem, newElem, element, opId)
                newElem.domNode = Some(element)
              }
            }

          case (oldComp: ComponentNode, newComp: ComponentNode) =>
            (oldComp.instance, newComp.instance) match {
              case (Some(oldInst), Some(newInst)) =>
                checkTreeDepth(newInst, opId)
                if (oldInst.componentType != newInst.componentType) {
                  runCleanupEffects(oldInst, opId)
                  replaceNode(oldComp, newComp, container, opId)
                  runInitialEffects(newInst, opId)
                } else {
                  // Diff the rendered output and run queued effects
                  oldInst.domNode.foreach { element =>
                    if (oldInst.rendered.isDefined && newInst.rendered.isDefined) {
                      diff(oldInst.rendered, newInst.rendered, element.asInstanceOf[dom.Element])
                      newInst.domNode = Some(element)
                      runQueuedEffects(newInst, opId)
                    }
                  }
                }
              case _ =>
                Logger.error(Category.VirtualDOM, "Invalid component instance state", opId)
            }

          case _ => replaceNode(old, new_, container, opId)
        }

      case (None, None) =>
        Logger.debug(Category.VirtualDOM, "No nodes to diff", opId)
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

  private def updateChildren(
      oldParent: ElementNode,
      newParent: ElementNode,
      container: dom.Element,
      opId: Int,
  ): Unit = {
    val oldChildren = oldParent.children
    val newChildren = newParent.children

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

    // Remove old keyed children not in new set
    oldKeyed.keys.foreach { key =>
      if (!newKeyed.contains(key)) {
        oldKeyed(key).domNode.foreach(container.removeChild)
      }
    }

    // Handle non-keyed children
    val oldNonKeyed = oldChildren.filter(_.key.isEmpty)
    val newNonKeyed = newChildren.filter(_.key.isEmpty)

    // Update existing children
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

//  private def runInitialEffects(instance: ComponentInstance, opId: Int): Unit = {
//    runQueuedEffects(instance, opId)
//
//    // Run effects for child components
//    instance.rendered.foreach { rendered =>
//      def runChildEffects(node: FluxusNode): Unit = {
//        node match {
//          case ComponentNode(_, _, Some(childInstance), _) =>
//            runInitialEffects(childInstance, opId)
//          case ElementNode(_, _, _, children, _, _, _, _, _) =>
//            children.foreach(runChildEffects)
//          case _ => // TextNodes have no children
//        }
//      }
//      runChildEffects(rendered)
//    }
//  }
//
//  private def runQueuedEffects(instance: ComponentInstance, opId: Int): Unit = {
//    if (instance.effects.nonEmpty) {
//      Logger.debug(
//        Category.StateEffect,
//        "Running queued effects",
//        opId,
//        Map(
//          "componentId" -> instance.id,
//          "effectCount" -> instance.effects.length,
//        ),
//      )
//
//      val effects = instance.effects
//      instance.effects = Vector.empty // Clear queue before running
//
//      effects.foreach { effect =>
//        try {
//          effect()
//        } catch {
//          case error: Throwable =>
//            Logger.error(
//              Category.StateEffect,
//              "Effect execution failed",
//              opId,
//              Map(
//                "componentId" -> instance.id,
//                "error"       -> error.getMessage,
//              ),
//            )
//            instance.hasEffectError = true
//        }
//      }
//    }
//  }

  private[fluxus] def runInitialEffects(instance: ComponentInstance, opId: Int, isMount: Boolean = false): Unit = {
    Logger.debug(
      Category.StateEffect,
      if (isMount) "Starting initial mount effect execution" else "Starting effect execution",
      opId,
      Map(
        "componentId"   -> instance.id,
        "componentType" -> instance.componentType,
        "effectCount"   -> instance.effects.length,
        "hasRendered"   -> instance.rendered.isDefined,
        "isMount"       -> isMount,
      ),
    )

    // Run any queued effects first
    runQueuedEffects(instance, opId)

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
                "parentIsMount"    -> isMount,
              ),
            )
            // Pass isMount flag to children
            runInitialEffects(childInstance, opId, isMount)

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
          "isMount"    -> isMount,
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
        "isMount"       -> isMount,
        "effectCount"   -> instance.effects.length,
      ),
    )
  }

  private def runQueuedEffects(instance: ComponentInstance, opId: Int): Unit = {
    if (instance.effects.nonEmpty) {
      Logger.debug(
        Category.StateEffect,
        "Running queued effects",
        opId,
        Map(
          "componentId" -> instance.id,
          "effectCount" -> instance.effects.length,
        ),
      )

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

      Logger.debug(
        Category.StateEffect,
        "Queued effects complete",
        opId,
        Map(
          "componentId" -> instance.id,
          "effectCount" -> effects.length,
        ),
      )
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
