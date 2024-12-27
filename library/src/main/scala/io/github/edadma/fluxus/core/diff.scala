package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ComponentNode, ElementNode, FluxusNode, RawNode, TextNode, logger}
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
      Seq(ReplaceNode(old, next))

    case (Some(old), Some(next)) =>
      diffSameType(old, next)
  }
}

private def sameNodeType(a: FluxusNode, b: FluxusNode): Boolean =
  (a, b) match {
    case (_: TextNode, _: TextNode)           => true
    case (e1: ElementNode, e2: ElementNode)   => e1.tag == e2.tag
    case (_: ComponentNode, _: ComponentNode) => true
    case (r1: RawNode, r2: RawNode)           => r1.element.tagName == r2.element.tagName
    case (_: TextNode, _)                     => false
    case (_: ElementNode, _)                  => false
    case (_: ComponentNode, _)                => false
    case (_: RawNode, _)                      => false
  }

private def diffSameType(oldNode: FluxusNode, newNode: FluxusNode): Seq[DOMOperation] = {
  (oldNode, newNode) match {
    case (old: RawNode, next: RawNode) =>
      // Transfer DOM node reference
      next.domNode = old.domNode

      if (old.element ne next.element) {
        Seq(ReplaceNode(old, next))
      } else Nil
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
      Seq(ReplaceNode(oldNode, newNode))
  }
}

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
  val (booleanChanges, regularChanges) = allKeys.filterNot(_.startsWith("=")).flatMap { key =>
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

  val propsToRemove = booleanChanges.collect { case Left(key) => key } ++
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

  // Property bindings (new)
  val propertyChanges = allKeys.filter(_.startsWith("=")).flatMap { key =>
    // For property bindings, compare against actual DOM property
    oldNode.domNode.map { domNode =>
      val propName = key.substring(1) // Remove "=" prefix
      val currentValue = propName match {
        case "checked"  => domNode.asInstanceOf[dom.html.Input].checked
        case "value"    => domNode.asInstanceOf[dom.html.Input].value
        case "selected" => domNode.asInstanceOf[dom.html.Option].selected
      }

      // Compare new desired value against current DOM property value
      newNode.attrs.get(key).map(newValue =>
        if (currentValue != newValue) Some(key -> newValue)
        else None,
      ).flatten
    }.getOrElse(None)
  }.toMap

  val ops = mutable.ArrayBuffer[DOMOperation]()

  if (propsToRemove.nonEmpty) ops += RemoveProps(oldNode, propsToRemove)
  if (propsToAdd.nonEmpty) ops += AddProps(oldNode, propsToAdd)
  if (propertyChanges.nonEmpty) ops += UpdateProperties(oldNode, propertyChanges)

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
    Seq(ReplaceNode(old, next))
  }
}
