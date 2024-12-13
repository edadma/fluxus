package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ElementNode, FluxusNode, TextNode, ComponentNode, logger}
import org.scalajs.dom

def reconcile(oldNode: Option[FluxusNode], newNode: Option[FluxusNode], container: dom.Element): Unit =
  logger.debug(
    "Starting reconciliation",
    category = "Reconciler",
    opId = 1,
    Map(
      "oldNode" -> oldNode.map(_.getClass.getSimpleName).getOrElse("None"),
      "newNode" -> newNode.map(_.getClass.getSimpleName).getOrElse("None"),
    ),
  )

  (oldNode, newNode) match
    case (None, None) =>
      // Nothing to do
      ()

    case (Some(_), None) =>
      // Remove old node
      unmount(oldNode.get)

    case (None, Some(node)) =>
      // Create and mount new node
      mount(node, container)

    case (Some(old), Some(next)) if !sameNodeType(old, next) =>
      // Different node types - replace entirely
      replace(old, next, container)

    case (Some(old), Some(next)) =>
      // Same type - update in place
      update(old, next)

private def sameNodeType(a: FluxusNode, b: FluxusNode): Boolean =
  (a, b) match
    case (_: TextNode, _: TextNode)           => true
    case (e1: ElementNode, e2: ElementNode)   => e1.tag == e2.tag
    case (_: ComponentNode, _: ComponentNode) => true
    case _                                    => false

private def mount(node: FluxusNode, container: dom.Element): Unit =
  logger.debug("Mounting new node", category = "Reconciler", opId = 1)
  // For now, just use our existing createDOM
  createDOM(node, container)

private def unmount(node: FluxusNode): Unit =
  logger.debug("Unmounting node", category = "Reconciler", opId = 1)
  // Remove from DOM
  node.domNode.foreach(_.parentNode.removeChild(_))

private def replace(oldNode: FluxusNode, newNode: FluxusNode, container: dom.Element): Unit =
  logger.debug("Replacing node", category = "Reconciler", opId = 1)
  unmount(oldNode)
  mount(newNode, container)

private def update(oldNode: FluxusNode, newNode: FluxusNode): Unit =
  logger.debug(
    "Updating node",
    category = "Reconciler",
    opId = 1,
    Map("nodeType" -> oldNode.getClass.getSimpleName),
  )

  (oldNode, newNode) match
    case (old: TextNode, next: TextNode) =>
      updateTextNode(old, next)

    case (old: ElementNode, next: ElementNode) =>
      updateElementNode(old, next)

    case (old: ComponentNode, next: ComponentNode) =>
      updateComponentNode(old, next)

    case _ =>
      logger.error(
        "Invalid node combination in update",
        category = "Reconciler",
        opId = 1,
        Map(
          "oldNode" -> oldNode.getClass.getSimpleName,
          "newNode" -> newNode.getClass.getSimpleName,
        ),
      )

private def updateTextNode(oldNode: TextNode, newNode: TextNode): Unit =
  // Only update if text changed
  if oldNode.text != newNode.text then
    oldNode.domNode.foreach(_.textContent = newNode.text)

private def updateElementNode(oldNode: ElementNode, newNode: ElementNode): Unit =
  // Update attributes
  oldNode.domNode.foreach { dom =>
    val element = dom.asInstanceOf[dom.Element]
    updateAttributes(element, oldNode.attrs, newNode.attrs)
    updateChildren(element, oldNode.children, newNode.children)
  }

private def updateComponentNode(oldNode: ComponentNode, newNode: ComponentNode): Unit =
  // For now, just re-render
  // Later we'll add state preservation, lifecycle methods, etc.
  val newRendered = newNode.component(newNode.props)
  oldNode.domNode.foreach { dom =>
    val parent = dom.parentNode.asInstanceOf[dom.Element]
    reconcile(Some(oldNode), Some(newRendered), parent)
  }

private def updateAttributes(
    element: dom.Element,
    oldAttrs: Map[String, Any],
    newAttrs: Map[String, Any],
): Unit =
  // Remove old attributes not in new set
  oldAttrs.keys.foreach { name =>
    if !newAttrs.contains(name) then
      element.removeAttribute(name)
  }

  // Add/update new attributes
  newAttrs.foreach { case (name, value) =>
    if oldAttrs.get(name) != Some(value) then
      element.setAttribute(name, value.toString)
  }

private def updateChildren(
    container: dom.Element,
    oldChildren: Vector[FluxusNode],
    newChildren: Vector[FluxusNode],
): Unit =
  // For now, just reconcile children one by one
  // Later we'll add key-based reconciliation
  val size = oldChildren.length max newChildren.length

  for i <- 0 until size do
    val oldChild = oldChildren.lift(i)
    val newChild = newChildren.lift(i)
    reconcile(oldChild, newChild, container)
