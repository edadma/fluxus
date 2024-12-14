package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ElementNode, FluxusNode, TextNode, ComponentNode, logger}
import org.scalajs.dom
import DOMOperation._

def diff(oldNode: Option[FluxusNode], newNode: Option[FluxusNode]): Seq[DOMOperation] = {
  logger.debug(
    "Diffing nodes",
    category = "Reconciler",
    opId = 1,
    Map(
      "oldNode" -> oldNode.map(_.getClass.getSimpleName).getOrElse("None"),
      "newNode" -> newNode.map(_.getClass.getSimpleName).getOrElse("None"),
    ),
  )

  (oldNode, newNode) match {
    case (None, None) => Nil

    case (Some(_), None) =>
      Seq(RemoveNode(oldNode.get))

    case (None, Some(node)) =>
      Seq(InsertNode(node))

    case (Some(old), Some(next)) if !sameNodeType(old, next) =>
      Seq(Replace(old, next))

    case (Some(old), Some(next)) =>
      diffSameType(old, next)
  }
}

private def sameNodeType(a: FluxusNode, b: FluxusNode): Boolean =
  (a, b) match {
    case (_: TextNode, _: TextNode)           => true
    case (e1: ElementNode, e2: ElementNode)   => e1.tag == e2.tag
    case (_: ComponentNode, _: ComponentNode) => true
    case (_: TextNode, _)                     => false
    case (_: ElementNode, _)                  => false
    case (_: ComponentNode, _)                => false
  }

private def diffSameType(oldNode: FluxusNode, newNode: FluxusNode): Seq[DOMOperation] = {
  (oldNode, newNode) match {
    case (old: TextNode, next: TextNode) =>
      if (old.text != next.text)
        Seq(UpdateText(old, next.text))
      else
        Nil

    case (old: ElementNode, next: ElementNode) =>
      val propChanges  = diffProps(old, next)
      val childChanges = diffChildren(old.children, next.children)
      propChanges ++ childChanges

    case (old: ComponentNode, next: ComponentNode) => diffComponents(old, next)
    case _ =>
      logger.error(
        "Invalid node combination in diffSameType",
        category = "Reconciler",
        opId = 1,
        Map(
          "oldNode" -> oldNode.getClass.getSimpleName,
          "newNode" -> newNode.getClass.getSimpleName,
        ),
      )
      assert(
        false,
        s"diffSameType called with incompatible nodes: ${oldNode.getClass.getSimpleName} and ${newNode.getClass.getSimpleName}",
      )
      Seq(Replace(oldNode, newNode))
  }
}

//private def diffProps(oldNode: ElementNode, newNode: ElementNode): Seq[DOMOperation] = {
//  val propsToRemove = oldNode.attrs.keySet -- newNode.attrs.keySet
//  val propsToAdd = newNode.attrs.filter { case (k, v) =>
//    !oldNode.attrs.get(k).contains(v)
//  }
//
//  // 1. Events that exist in old but not in new should be removed
//  val removedEvents = oldNode.events.keySet -- newNode.events.keySet
//
//  // 2. New events that didn't exist before should be added
//  val addedEvents = newNode.events.keySet -- oldNode.events.keySet
//
//  // 3. For events that exist in both, we need to update if they've changed
//  val commonEvents = oldNode.events.keySet.intersect(newNode.events.keySet)
//
//  // Track which common events need updating by comparing the function references
//  val eventsToUpdate = commonEvents.filter { eventName =>
//    // If the function references are different, we need to update
//    oldNode.events(eventName) ne newNode.events(eventName)
//  }
//
//  val eventsToRemove = removedEvents ++ eventsToUpdate
//  val eventsToAdd = newNode.events.filter { case (name, _) =>
//    addedEvents.contains(name) || eventsToUpdate.contains(name)
//  }
//
//  if (
//    propsToRemove.nonEmpty || propsToAdd.nonEmpty ||
//    eventsToRemove.nonEmpty || eventsToAdd.nonEmpty
//  )
//    Seq(UpdateProps(oldNode, propsToRemove, propsToAdd, eventsToRemove, eventsToAdd))
//  else
//    Nil
//}

private def diffProps(oldNode: ElementNode, newNode: ElementNode): Seq[DOMOperation] = {
  logger.debug(
    "Starting props diff",
    category = "Reconciler",
    opId = 1,
    Map(
      "oldEvents" -> oldNode.events.keySet.toString,
      "newEvents" -> newNode.events.keySet.toString,
    ),
  )

  val propsToRemove = oldNode.attrs.keySet -- newNode.attrs.keySet
  val propsToAdd = newNode.attrs.filter { case (k, v) =>
    !oldNode.attrs.get(k).contains(v)
  }

  // 1. Events that exist in old but not in new should be removed
  val removedEvents = oldNode.events.keySet -- newNode.events.keySet

  logger.debug(
    "Identified removed events",
    category = "Reconciler",
    opId = 1,
    Map("removedEvents" -> removedEvents.toString),
  )

  // 2. New events that didn't exist before should be added
  val addedEvents = newNode.events.keySet -- oldNode.events.keySet

  logger.debug(
    "Identified added events",
    category = "Reconciler",
    opId = 1,
    Map("addedEvents" -> addedEvents.toString),
  )

  // 3. For events that exist in both, we need to update if they've changed
  val commonEvents = oldNode.events.keySet.intersect(newNode.events.keySet)

  // Track which common events need updating by comparing the function references
  val eventsToUpdate = commonEvents.filter { eventName =>
    val needsUpdate = oldNode.events(eventName) ne newNode.events(eventName)
    logger.debug(
      s"Checking event $eventName for updates",
      category = "Reconciler",
      opId = 1,
      Map("needsUpdate" -> needsUpdate.toString),
    )
    needsUpdate
  }

  val eventsToRemove = removedEvents ++ eventsToUpdate
  val eventsToAdd = newNode.events.filter { case (name, _) =>
    addedEvents.contains(name) || eventsToUpdate.contains(name)
  }

  logger.debug(
    "Final event changes",
    category = "Reconciler",
    opId = 1,
    Map(
      "eventsToRemove" -> eventsToRemove.toString,
      "eventsToAdd"    -> eventsToAdd.toString,
    ),
  )

  if (
    propsToRemove.nonEmpty || propsToAdd.nonEmpty ||
    eventsToRemove.nonEmpty || eventsToAdd.nonEmpty
  )
    Seq(UpdateProps(oldNode, propsToRemove, propsToAdd, eventsToRemove, eventsToAdd))
  else
    Nil
}

private def diffChildren(
    oldChildren: Vector[FluxusNode],
    newChildren: Vector[FluxusNode],
): Seq[DOMOperation] = {
  val oldKeyedNodes = oldChildren.flatMap { child =>
    child.key.map(_ -> child)
  }.toMap

  logger.debug(
    "Built old keyed nodes map",
    category = "Reconciler",
    opId = 1,
    Map(
      "oldChildren" -> oldChildren.map(_.key).mkString(", "),
      "newChildren" -> newChildren.map(_.key).mkString(", "),
      "keyedNodes"  -> oldKeyedNodes.keys.mkString(", "),
    ),
  )

  // First handle removals and updates for keyed nodes
  val (operations, remainingOld) = newChildren.zipWithIndex.foldLeft(
    (Vector.empty[DOMOperation], oldChildren.toSet),
  ) { case ((ops, remaining), (newChild, newIndex)) =>
    newChild.key match {
      case Some(key) =>
        oldKeyedNodes.get(key) match {
          case Some(oldChild) =>
            // Found matching key - update node
            (ops ++ diff(Some(oldChild), Some(newChild)), remaining - oldChild)
          case None =>
            // No matching key - insert new node
            (ops :+ InsertNode(newChild, Some(newIndex)), remaining)
        }
      case None =>
        // Non-keyed node - use index-based matching
        val oldChild = oldChildren.lift(newIndex)
        (ops ++ diff(oldChild.filter(_.key.isEmpty), Some(newChild)), oldChild.map(remaining - _).getOrElse(remaining))
    }
  }

  // Remove any remaining old nodes that weren't reused
  val removals = remainingOld.toVector.map(RemoveNode)

  logger.debug(
    "Diffing children complete",
    category = "Reconciler",
    opId = 1,
    Map(
      "operations" -> operations.mkString(", "),
      "removals"   -> removals.mkString(", "),
    ),
  )

  operations ++ removals
}

private def diffComponents(old: ComponentNode, next: ComponentNode): Seq[DOMOperation] = {
  logger.debug(
    "Diffing components",
    category = "Reconciler",
    opId = 1,
    Map(
      "oldKey"   -> old.key.getOrElse("none"),
      "newKey"   -> next.key.getOrElse("none"),
      "oldProps" -> old.props.toString,
      "newProps" -> next.props.toString,
    ),
  )

  // If keys match or both have no keys, update props if needed
  if (old.key == next.key) {
    if (old.props != next.props) {
      Seq(RerenderComponent(old, next))
    } else Nil
  } else {
    // Different keys means treat as different components
    Seq(Replace(old, next))
  }
}
