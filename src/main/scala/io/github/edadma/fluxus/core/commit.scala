package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.logger
import org.scalajs.dom

def commit(ops: Seq[DOMOperation], container: dom.Element): Unit = ops.foreach(op => commit(op, container))

def commit(op: DOMOperation, container: dom.Element): Unit = {
  logger.debug(
    "Committing operation",
    category = "DOM",
    Map(
      "operation"     -> op.toString,
      "operationType" -> op.getClass.getSimpleName,
      "hasNode" -> (op match {
        case UpdateText(node, _) => s"domNode: ${node.domNode.isDefined}"
        case _                   => "not text update"
      }),
    ),
  )

  op match {
    case Replace(oldNode, newNode) =>
      oldNode.domNode.foreach { old =>
        val dom = createDOMNode(newNode)
        old.parentNode.replaceChild(dom, old)
      }

    case UpdateText(node, newText) =>
      node.domNode.foreach { old =>
        logger.debug(
          "Updating text node",
          category = "Reconciler",
          Map(
            "oldText"  -> old.textContent,
            "newText"  -> newText,
            "nodeType" -> old.nodeType.toString,
            "domNode"  -> old.toString,
          ),
        )
        old.textContent = newText
        logger.debug(
          "After text update",
          category = "Reconciler",
          Map("currentText" -> old.textContent),
        )
      }

    case RemoveProps(node, props) =>
      node.domNode.foreach { n =>
        val element = n.asInstanceOf[dom.Element]
        props.foreach(element.removeAttribute)
      }

    case AddProps(node, props) =>
      node.domNode.foreach { n =>
        val element = n.asInstanceOf[dom.Element]
        props.foreach { case (name, value) =>
          element.setAttribute(name, value.toString)
        }
      }

    case RemoveEvent(node, eventName) =>
      node.domNode.foreach { n =>
        val element = n.asInstanceOf[dom.Element]
        val domEventName = eventName.toLowerCase match {
          case name if name.startsWith("on") => name.substring(2)
          case name                          => name
        }
        node.events.get(eventName).foreach { handler =>
          element.removeEventListener(domEventName, handler)
        }
        node.events = node.events - eventName
      }

    case AddEvent(node, eventName, handler) =>
      node.domNode.foreach { n =>
        val element = n.asInstanceOf[dom.Element]
        val domEventName = eventName.toLowerCase match {
          case name if name.startsWith("on") => name.substring(2)
          case name                          => name
        }
        element.addEventListener(domEventName, handler)
        node.events = node.events + (eventName -> handler)
      }

    case RemoveNode(node) =>
      node.domNode.foreach { dom =>
        if (dom.parentNode != null) {
          logger.debug(
            "Removing node",
            category = "Reconciler",
            opId = 1,
            Map(
              "node"    -> node.toString,
              "domNode" -> dom.toString,
              "parent"  -> dom.parentNode.toString,
            ),
          )
          dom.parentNode.removeChild(dom)
        } else {
          logger.warn(
            "Attempted to remove node with no parent",
            category = "Reconciler",
            opId = 1,
            Map("node" -> node.toString),
          )
        }
      }

    case InsertNode(node, parentNode, position) =>
      val dom             = createDOMNode(node)
      val targetContainer = parentNode.domNode.getOrElse(container)

      position match {
        case Some(idx) if idx < targetContainer.childNodes.length =>
          targetContainer.insertBefore(dom, targetContainer.childNodes(idx))
        case _ =>
          targetContainer.appendChild(dom)
      }

    case RerenderComponent(old, newProps) =>
      logger.debug(
        "Handling RerenderComponent",
        category = "Reconciler",
        Map(
          "oldInstanceId" -> old.instance.map(_.id).getOrElse("none"),
          "newInstanceId" -> newProps.instance.map(_.id).getOrElse("none"),
          "sameInstance"  -> old.instance.zip(newProps.instance).map(_._1 eq _._2).getOrElse(false).toString,
        ),
      )

//      val rendered = ComponentInstance.withInstance(old.instance.get) {
//        newProps.component(newProps.props)
//      }
//
//      old.instance.foreach(_.rendered = Some(rendered))
//      old.domNode.foreach { n =>
//        val parent = n.parentNode.asInstanceOf[dom.Element]
//        val newDom = createDOMNode(rendered)
//        parent.replaceChild(newDom, n)
//      }

      // Let the instance handle its own rerendering - it will take care of:
      // 1. Resetting hookIndex
      // 2. Creating new rendered tree
      // 3. Reconciling with old tree
      // 4. Updating DOM through reconciliation
      old.instance.foreach(_.rerender())

    case MoveNode(node, toIndex) =>
      node.domNode.foreach { dom =>
        val parent = dom.parentNode
        if (toIndex >= parent.childNodes.length) {
          parent.appendChild(dom)
        } else {
          parent.insertBefore(dom, parent.childNodes(toIndex))
        }
      }
  }
}
