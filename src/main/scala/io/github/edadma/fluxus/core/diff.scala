package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ComponentNode, ElementNode, FluxusNode, TextNode, logger}
import org.scalajs.dom

import scala.collection.mutable

def diff(
    oldNode: Option[FluxusNode],
    newNode: Option[FluxusNode],
    parent: Option[FluxusNode] = None,
): Seq[DOMOperation] = {
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
      Seq(InsertNode(node, parent.getOrElse(node), None))

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
      // Transfer DOM reference to new node
      next.domNode = old.domNode

      if (old.text != next.text)
        Seq(UpdateText(old, next.text))
      else
        Nil

    case (old: ElementNode, next: ElementNode) =>
      val propChanges  = diffProps(old, next)
      val childChanges = diffChildren(old.children, next.children, old)
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

//private def diffProps(oldNode: ElementNode, newNode: ElementNode): Seq[DOMOperation] = {
//  logger.debug(
//    "Starting props diff",
//    category = "Reconciler",
//    opId = 1,
//    Map(
//      "oldEvents" -> oldNode.events.keySet.toString,
//      "newEvents" -> newNode.events.keySet.toString,
//    ),
//  )
//
//  val propsToRemove = oldNode.attrs.keySet -- newNode.attrs.keySet
//  val propsToAdd = newNode.attrs.filter { case (k, v) =>
//    !oldNode.attrs.get(k).contains(v)
//  }
//
//  // 1. Events that exist in old but not in new should be removed
//  val eventsToRemove = oldNode.events.keySet -- newNode.events.keySet
//
//  logger.debug(
//    "Identified removed events",
//    category = "Reconciler",
//    opId = 1,
//    Map("eventsToRemove" -> eventsToRemove.toString),
//  )
//
//  // 2. New events that didn't exist before should be added
//  val eventsToAdd = newNode.events.keySet -- oldNode.events.keySet
//
//  logger.debug(
//    "Identified added events",
//    category = "Reconciler",
//    opId = 1,
//    Map("addedEvents" -> eventsToAdd.toString),
//  )
//
//  // 3. For events that exist in both, we need to update if they've changed
//  val commonEvents = oldNode.events.keySet.intersect(newNode.events.keySet)
//
//  val eventsToUpdate = commonEvents.filter { eventName =>
//    oldNode.events(eventName) ne newNode.events(eventName)
//  }
//
//  if (eventsToUpdate.nonEmpty) {
//    logger.debug(
//      "Updating event handlers",
//      category = "Reconciler",
//      Map(
//        "events"  -> eventsToUpdate.toString,
//        "domNode" -> oldNode.domNode.isDefined.toString,
//      ),
//    )
//  }
//
//  // Keep the original DOM node reference
//  newNode.domNode = oldNode.domNode
//
//  logger.debug(
//    "Final event changes",
//    category = "Reconciler",
//    opId = 1,
//    Map(
//      "eventsToRemove" -> eventsToRemove.toString,
//      "eventsToAdd"    -> eventsToAdd.toString,
//    ),
//  )
//
//  val ops = mutable.ArrayBuffer[DOMOperation]()
//
//  if (propsToRemove.nonEmpty) ops += RemoveProps(oldNode, propsToRemove)
//  if (propsToAdd.nonEmpty) ops += AddProps(oldNode, propsToAdd)
//
//  // Remove old event handlers
//  eventsToRemove.foreach(name => ops += RemoveEvent(oldNode, name))
//
//  // Add new event handlers
//  eventsToAdd.foreach { name =>
//    ops += AddEvent(oldNode, name, newNode.events(name))
//  }
//
//  // Update changed handlers (remove old + add new)
//  eventsToUpdate.foreach { name =>
//    ops += RemoveEvent(oldNode, name)
//    ops += AddEvent(oldNode, name, newNode.events(name))
//  }
//
//  ops.toSeq
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

  // First identify all attributes that need handling
  val allKeys = oldNode.attrs.keySet ++ newNode.attrs.keySet

  // Split into regular and boolean attributes that need updates
  val (booleanChanges, regularChanges) = allKeys.flatMap { key =>
    (oldNode.attrs.get(key), newNode.attrs.get(key)) match {
      // Boolean attribute changes
      case (Some(oldValue: Boolean), Some(newValue: Boolean)) if oldValue != newValue =>
        if (newValue)
          Some(Right((key, newValue))) // Add boolean attribute
        else
          Some(Left(key)) // Remove boolean attribute

      // Regular attribute changes
      case (oldValue, newValue) if oldValue != newValue =>
        if (newValue.isEmpty)
          Some(Left(key)) // Remove if not in new node
        else
          Some(Right((key, newValue.get))) // Add/update with new value

      case _ => None
    }
  }.partition {
    case Left(_)                => true
    case Right((_, v: Boolean)) => !v // false booleans go to remove
    case _                      => false
  }

  val propsToRemove = booleanChanges.collect { case Left(key) => key }.toSet ++
    booleanChanges.collect { case Right((k, _)) => k }
  val propsToAdd = regularChanges.collect { case Right((k, v)) => k -> v }.toMap

  // 1. Events that exist in old but not in new should be removed
  val eventsToRemove = oldNode.events.keySet -- newNode.events.keySet

  logger.debug(
    "Identified removed events",
    category = "Reconciler",
    opId = 1,
    Map("eventsToRemove" -> eventsToRemove.toString),
  )

  // 2. New events that didn't exist before should be added
  val eventsToAdd = newNode.events.keySet -- oldNode.events.keySet

  logger.debug(
    "Identified added events",
    category = "Reconciler",
    opId = 1,
    Map("addedEvents" -> eventsToAdd.toString),
  )

  // 3. For events that exist in both, we need to update if they've changed
  val commonEvents = oldNode.events.keySet.intersect(newNode.events.keySet)

  val eventsToUpdate = commonEvents.filter { eventName =>
    oldNode.events(eventName) ne newNode.events(eventName)
  }

  if (eventsToUpdate.nonEmpty) {
    logger.debug(
      "Updating event handlers",
      category = "Reconciler",
      Map(
        "events"  -> eventsToUpdate.toString,
        "domNode" -> oldNode.domNode.isDefined.toString,
      ),
    )
  }

  // Keep the original DOM node reference
  newNode.domNode = oldNode.domNode

  logger.debug(
    "Final event changes",
    category = "Reconciler",
    opId = 1,
    Map(
      "eventsToRemove" -> eventsToRemove.toString,
      "eventsToAdd"    -> eventsToAdd.toString,
    ),
  )

  val ops = mutable.ArrayBuffer[DOMOperation]()

  if (propsToRemove.nonEmpty) ops += RemoveProps(oldNode, propsToRemove)
  if (propsToAdd.nonEmpty) ops += AddProps(oldNode, propsToAdd)

  // Remove old event handlers
  eventsToRemove.foreach(name => ops += RemoveEvent(oldNode, name))

  // Add new event handlers
  eventsToAdd.foreach { name =>
    ops += AddEvent(oldNode, name, newNode.events(name))
  }

  // Update changed handlers (remove old + add new)
  eventsToUpdate.foreach { name =>
    ops += RemoveEvent(oldNode, name)
    ops += AddEvent(oldNode, name, newNode.events(name))
  }

  ops.toSeq
}

//private def diffChildren(
//    oldChildren: Vector[FluxusNode],
//    newChildren: Vector[FluxusNode],
//): Seq[DOMOperation] = {
//  // Build map of keyed nodes and their current positions
//  val oldKeyedNodes = oldChildren.zipWithIndex.flatMap { case (child, index) =>
//    child.key.map(key => key -> (child, index))
//  }.toMap
//
//  logger.debug(
//    "Built old keyed nodes map",
//    category = "Reconciler",
//    opId = 1,
//    Map(
//      "oldChildren" -> oldChildren.map(_.key).mkString(", "),
//      "newChildren" -> newChildren.map(_.key).mkString(", "),
//      "keyedNodes"  -> oldKeyedNodes.keys.mkString(", "),
//    ),
//  )
//
//  // First handle removals and updates for keyed nodes
//  val (operations, remainingOld) = newChildren.zipWithIndex.foldLeft(
//    (Vector.empty[DOMOperation], oldChildren.toSet),
//  ) { case ((ops, remaining), (newChild, newIndex)) =>
//    newChild.key match {
//      case Some(key) =>
//        oldKeyedNodes.get(key) match {
//          case Some((oldChild, oldIndex)) =>
//            // Found matching key - update if needed and move to new position if different
//            val updateOps = diff(Some(oldChild), Some(newChild))
//            val moveOps =
//              if (oldIndex != newIndex)
//                Seq(MoveNode(oldChild, newIndex))
//              else
//                Nil
//            (ops ++ updateOps ++ moveOps, remaining - oldChild)
//          case None =>
//            // No matching key - insert new node
//            (ops :+ InsertNode(newChild, Some(newIndex)), remaining)
//        }
//      case None =>
//        // Non-keyed node - use index-based matching
//        val oldChild = oldChildren.lift(newIndex)
//        if (oldChild.exists(old => old.key.isEmpty)) {
//          // Both nodes are non-keyed, do regular diff
//          (ops ++ diff(oldChild, Some(newChild)), oldChild.map(remaining - _).getOrElse(remaining))
//        } else {
//          // Old node was keyed or doesn't exist - insert new
//          (ops :+ InsertNode(newChild, Some(newIndex)), remaining)
//        }
//    }
//  }
//
//  // Remove any remaining old nodes that weren't reused
//  val removals = remainingOld.toVector.map(RemoveNode.apply)
//
//  logger.debug(
//    "Diffing children complete",
//    category = "Reconciler",
//    opId = 1,
//    Map(
//      "operations" -> operations.mkString(", "),
//      "removals"   -> removals.mkString(", "),
//    ),
//  )
//
//  operations ++ removals
//}

//private def diffChildren(
//    oldChildren: Vector[FluxusNode],
//    newChildren: Vector[FluxusNode],
//): Seq[DOMOperation] = {
//  // First, build map of keyed nodes and their positions from old children
//  val oldKeyedNodes = oldChildren.zipWithIndex.flatMap { case (child, index) =>
//    child.key.map(key => key -> (child, index))
//  }.toMap
//
//  logger.debug(
//    "Starting child reconciliation",
//    category = "Reconciler",
//    Map(
//      "oldChildren" -> oldChildren.map(_.key).mkString(", "),
//      "newChildren" -> newChildren.map(_.key).mkString(", "),
//      "keyedNodes"  -> oldKeyedNodes.keys.mkString(", "),
//    ),
//  )
//
//  // Process new children and track remaining old nodes for removal
//  val (operations, remainingOld) = newChildren.zipWithIndex.foldLeft(
//    (Vector.empty[DOMOperation], oldChildren.toSet),
//  ) { case ((ops, remaining), (newChild, newIndex)) =>
//    logger.debug(
//      "Processing child",
//      category = "Reconciler",
//      Map(
//        "newChildKey"   -> newChild.key.getOrElse("none"),
//        "newIndex"      -> newIndex.toString,
//        "remainingKeys" -> remaining.map(_.key).mkString(", "),
//      ),
//    )
//
//    newChild.key match {
//      case Some(key) =>
//        // Handle keyed nodes
//        oldKeyedNodes.get(key) match {
//          case Some((oldChild, oldIndex)) =>
//            // Found matching key - update if needed and move to new position if different
//            val updateOps = diff(Some(oldChild), Some(newChild))
//            val moveOps =
//              if (oldIndex != newIndex)
//                Seq(MoveNode(oldChild, newIndex))
//              else
//                Nil
//
//            logger.debug(
//              "Matched keyed nodes",
//              category = "Reconciler",
//              Map(
//                "key"       -> key,
//                "oldIndex"  -> oldIndex.toString,
//                "newIndex"  -> newIndex.toString,
//                "needsMove" -> (oldIndex != newIndex).toString,
//              ),
//            )
//
//            (ops ++ updateOps ++ moveOps, remaining - oldChild)
//
//          case None =>
//            // No matching key - insert new node
//            logger.debug(
//              "No matching key found - inserting",
//              category = "Reconciler",
//              Map("key" -> key),
//            )
//            (ops :+ InsertNode(newChild, Some(newIndex)), remaining)
//        }
//
//      case None =>
//        // Handle non-keyed nodes using position matching
//        val oldChild = oldChildren.lift(newIndex)
//        if (oldChild.exists(old => old.key.isEmpty)) {
//          // Both nodes are non-keyed, do regular diff
//          logger.debug(
//            "Matched non-keyed nodes by position",
//            category = "Reconciler",
//            Map("index" -> newIndex.toString),
//          )
//          (ops ++ diff(oldChild, Some(newChild)), oldChild.map(remaining - _).getOrElse(remaining))
//        } else {
//          // Old node was keyed or doesn't exist - insert new
//          logger.debug(
//            "No matching non-keyed node - inserting",
//            category = "Reconciler",
//            Map("index" -> newIndex.toString),
//          )
//          (ops :+ InsertNode(newChild, Some(newIndex)), remaining)
//        }
//    }
//  }
//
//  // Generate removal operations for any unmatched old nodes
//  val removals = remainingOld.toVector.map(RemoveNode.apply)
//
//  logger.debug(
//    "Diffing children complete",
//    category = "Reconciler",
//    Map(
//      "operations" -> operations.mkString(", "),
//      "removals"   -> removals.mkString(", "),
//    ),
//  )
//
//  operations ++ removals
//}

//private def diffChildren(
//    oldChildren: Vector[FluxusNode],
//    newChildren: Vector[FluxusNode],
//    parent: FluxusNode, // Add parent parameter
//): Seq[DOMOperation] = {
//  // First, build map of keyed nodes and their positions from old children
//  val oldKeyedNodes = oldChildren.zipWithIndex.flatMap { case (child, index) =>
//    child.key.map(key => key -> (child, index))
//  }.toMap
//
//  logger.debug(
//    "Starting child reconciliation",
//    category = "Reconciler",
//    Map(
//      "oldChildren" -> oldChildren.map(_.key).mkString(", "),
//      "newChildren" -> newChildren.map(_.key).mkString(", "),
//      "keyedNodes"  -> oldKeyedNodes.keys.mkString(", "),
//    ),
//  )
//
//  // Process new children and track remaining old nodes for removal
//  val (operations, remainingOld) = newChildren.zipWithIndex.foldLeft(
//    (Vector.empty[DOMOperation], oldChildren.toSet),
//  ) { case ((ops, remaining), (newChild, newIndex)) =>
//    newChild.key match {
//      case Some(key) =>
//        // Handle keyed nodes
//        oldKeyedNodes.get(key) match {
//          case Some((oldChild, oldIndex)) =>
//            // Found matching key - update if needed and move to new position if different
//            val updateOps = diff(Some(oldChild), Some(newChild), Some(parent))
//            val moveOps =
//              if (oldIndex != newIndex)
//                Seq(MoveNode(oldChild, newIndex))
//              else
//                Nil
//            (ops ++ updateOps ++ moveOps, remaining - oldChild)
//
//          case None =>
//            // No matching key - insert new node with parent info
//            logger.debug(
//              "No matching key found - inserting",
//              category = "Reconciler",
//              Map("key" -> key),
//            )
//            // Changed here: Include parent in InsertNode
//            (ops :+ InsertNode(newChild, parent, Some(newIndex)), remaining)
//        }
//
//      case None =>
//        // Handle non-keyed nodes using position matching
//        val oldChild = oldChildren.lift(newIndex)
//        if (oldChild.exists(old => old.key.isEmpty)) {
//          // Both nodes are non-keyed, do regular diff
//          (ops ++ diff(oldChild, Some(newChild)), oldChild.map(remaining - _).getOrElse(remaining))
//        } else {
//          // Old node was keyed or doesn't exist - insert new with parent info
//          // Changed here: Include parent in InsertNode
//          (ops :+ InsertNode(newChild, parent, Some(newIndex)), remaining)
//        }
//    }
//  }
//
//  // Generate removal operations for any unmatched old nodes
//  val removals = remainingOld.toVector.map(RemoveNode.apply)
//
//  operations ++ removals
//}

/*
private def diffChildren(
    oldChildren: Vector[FluxusNode],
    newChildren: Vector[FluxusNode],
    parent: FluxusNode,
): Seq[DOMOperation] = {
  // First, build map of keyed nodes from old children
  val oldKeyedNodes = oldChildren.zipWithIndex.flatMap { case (child, index) =>
    child.key.map(key => key -> (child, index))
  }.toMap

  logger.debug(
    "Starting child reconciliation",
    category = "Reconciler",
    Map(
      "oldChildren" -> oldChildren.map(_.key).mkString(", "),
      "newChildren" -> newChildren.map(_.key).mkString(", "),
      "keyedNodes"  -> oldKeyedNodes.keys.mkString(", "),
    ),
  )

  val operations = mutable.ArrayBuffer[DOMOperation]()
  val handledOld = mutable.Set[FluxusNode]()
  val handledNew = mutable.Set[FluxusNode]()

  // First pass: Handle all keyed nodes
  newChildren.zipWithIndex.foreach { case (newChild, newIndex) =>
    newChild.key.foreach { key =>
      oldKeyedNodes.get(key).foreach { case (oldChild, oldIndex) =>
        handledOld += oldChild
        handledNew += newChild

        // Generate updates if needed
        operations ++= diff(Some(oldChild), Some(newChild), Some(parent))

        // Move if position changed
        if (oldIndex != newIndex) {
          operations += MoveNode(oldChild, newIndex)
        }
      }
    }
  }

  // Second pass: Handle non-keyed nodes using position matching
  val remainingOld = oldChildren.filterNot(handledOld.contains)
  val remainingNew = newChildren.filterNot(handledNew.contains)

  remainingNew.zipWithIndex.foreach { case (newChild, relativeIdx) =>
    val absoluteIdx = handledNew.count(_ => true) + relativeIdx

    remainingOld.lift(relativeIdx) match {
      case Some(oldChild) if sameNodeType(oldChild, newChild) =>
        handledOld += oldChild
        handledNew += newChild

        // Update content if needed
        operations ++= diff(Some(oldChild), Some(newChild), Some(parent))

        // Move if position changed
        val oldAbsoluteIdx = oldChildren.indexOf(oldChild)
        if (oldAbsoluteIdx != absoluteIdx) {
          operations += MoveNode(oldChild, absoluteIdx)
        }

      case _ =>
        // Insert new node at correct position
        operations += InsertNode(newChild, parent, Some(absoluteIdx))
    }
  }

  // Final pass: Remove any unmatched old nodes
  oldChildren.filterNot(handledOld.contains).foreach { oldChild =>
    operations += RemoveNode(oldChild)
  }

  logger.debug(
    "Diffing children complete",
    category = "Reconciler",
    Map(
      "operations" -> operations.mkString(", "),
      "removals"   -> oldChildren.filterNot(handledOld.contains).mkString(", "),
    ),
  )

  operations.toSeq
}
 */

private def diffChildren(
    oldChildren: Vector[FluxusNode],
    newChildren: Vector[FluxusNode],
    parent: FluxusNode,
): Seq[DOMOperation] = {
  // Build map of keyed nodes and their positions from old children
  val oldKeyedNodes = oldChildren.zipWithIndex.flatMap { case (child, index) =>
    child.key.map(key => key -> (child, index))
  }.toMap

  logger.debug(
    "Starting child reconciliation",
    category = "Reconciler",
    Map(
      "oldChildren" -> oldChildren.map(_.key).mkString(", "),
      "newChildren" -> newChildren.map(_.key).mkString(", "),
      "keyedNodes"  -> oldKeyedNodes.keys.mkString(", "),
    ),
  )

  val operations       = mutable.ArrayBuffer[DOMOperation]()
  val handledOld       = mutable.Set[FluxusNode]()
  val handledPositions = mutable.Set[Int]()

  // First pass: Handle all keyed nodes and moves
  newChildren.zipWithIndex.foreach { case (newChild, newIndex) =>
    newChild.key match {
      case Some(key) =>
        oldKeyedNodes.get(key) match {
          case Some((oldChild, oldIndex)) =>
            handledOld += oldChild
            handledPositions += newIndex

            // Generate content updates if needed
            operations ++= diff(Some(oldChild), Some(newChild), Some(parent))

            // If position changed, move the node
            if (oldIndex != newIndex) {
              operations += MoveNode(oldChild, newIndex)
              logger.debug(
                "Moving keyed node",
                category = "Reconciler",
                Map(
                  "key"  -> key,
                  "from" -> oldIndex.toString,
                  "to"   -> newIndex.toString,
                ),
              )
            }

          case None =>
            // New keyed node - insert
            operations += InsertNode(newChild, parent, Some(newIndex))
        }

      case None =>
        // Handle non-keyed nodes
        oldChildren.lift(newIndex).filter(old => old.key.isEmpty && !handledOld.contains(old)) match {
          case Some(oldChild) =>
            handledOld += oldChild
            handledPositions += newIndex
            operations ++= diff(Some(oldChild), Some(newChild), Some(parent))

          case None =>
            operations += InsertNode(newChild, parent, Some(newIndex))
        }
    }
  }

  // Remove any unmatched old nodes
  oldChildren.filterNot(handledOld.contains).foreach { oldChild =>
    operations += RemoveNode(oldChild)
  }

  logger.debug(
    "Diffing children complete",
    category = "Reconciler",
    Map(
      "operations" -> operations.mkString(", "),
      "removals"   -> oldChildren.filterNot(handledOld.contains).mkString(", "),
    ),
  )

  operations.toSeq
}

private def diffComponents(old: ComponentNode, next: ComponentNode): Seq[DOMOperation] = {
  val oldKey   = old.key
  val newKey   = next.key
  val sameType = old.component.getClass == next.component.getClass

  logger.debug(
    "Diffing components",
    category = "Reconciler",
    opId = 1,
    Map(
      "oldKey"                    -> oldKey.getOrElse("none"),
      "newKey"                    -> newKey.getOrElse("none"),
      "oldProps"                  -> old.props.toString,
      "newProps"                  -> next.props.toString,
      "oldInstanceId"             -> old.instance.map(_.id).getOrElse("none"),
      "newInstanceBeforeTransfer" -> next.instance.map(_.id).getOrElse("none"),
      "sameType"                  -> sameType.toString,
    ),
  )

  // Reuse instance if keys match (or both have no key) and component types match
  if (oldKey == newKey && sameType) {
    // Transfer the instance and maintain all its state
    old.instance.foreach { instance =>
      next.instance = Some(instance)
      // Update the node reference in the instance
      instance.node = next
      // Transfer DOM node reference
      next.domNode = old.domNode
    }

    logger.debug(
      "After instance transfer",
      category = "Reconciler",
      Map(
        "oldInstanceId" -> old.instance.map(_.id).getOrElse("none"),
        "newInstanceId" -> next.instance.map(_.id).getOrElse("none"),
        "sameInstance"  -> old.instance.zip(next.instance).map(_._1 eq _._2).getOrElse(false).toString,
      ),
    )

    if (old.props != next.props) {
      Seq(RerenderComponent(old, next))
    } else Nil
  } else {
    // Different keys or component types means treat as different components
    Seq(Replace(old, next))
  }
}
