package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.logger
import org.scalajs.dom
import DOMOperation._

def commit(op: DOMOperation, container: dom.Element): Unit = {
  logger.debug(
    "Committing DOM operation",
    category = "Reconciler",
    opId = 1,
    Map("operation" -> op.toString, "container" -> container.toString),
  )

  op match {
    case Replace(oldNode, newNode) =>
      oldNode.domNode.foreach { old =>
        val dom = createDOMNode(newNode)
        old.parentNode.replaceChild(dom, old)
      }

    case UpdateText(node, newText) =>
      node.domNode.foreach(_.textContent = newText)

    case UpdateProps(node, propsToRemove, propsToAdd, eventsToRemove, eventsToAdd) =>
      node.domNode.foreach { n =>
        val element = n.asInstanceOf[dom.Element]

        // Update regular props
        propsToRemove.foreach(element.removeAttribute)
        propsToAdd.foreach { case (name, value) =>
          element.setAttribute(name, value.toString)
        }

        logger.debug(
          "Removing events",
          category = "Reconciler",
          opId = 1,
          Map(
            "eventsToRemove" -> eventsToRemove.toString,
            "nodeEvents"     -> node.events.toString,
          ),
        )

        // Update events - much simpler now with pre-wrapped handlers
        eventsToRemove.foreach { eventName =>
          val domEventName = eventName.toLowerCase match {
            case name if name.startsWith("on") => name.substring(2)
            case name                          => name
          }

          logger.debug(
            "Removing event listener",
            category = "Reconciler",
            opId = 1,
            Map(
              "eventName"    -> eventName,
              "domEventName" -> domEventName,
              "handlerFound" -> node.events.contains(eventName).toString,
            ),
          )

          node.events.get(eventName).foreach { handler =>
            element.removeEventListener(domEventName, handler)
          }
        }

        eventsToAdd.foreach { case (eventName, handler) =>
          val domEventName = eventName.toLowerCase match {
            case name if name.startsWith("on") => name.substring(2)
            case name                          => name
          }
          element.addEventListener(domEventName, handler)
        }
      }

      // Maintain events map
      node.events = node.events -- eventsToRemove ++ eventsToAdd

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

    case InsertNode(node, position) =>
      val dom = createDOMNode(node)
      position match {
        case Some(idx) if idx < container.childNodes.length =>
          container.insertBefore(dom, container.childNodes(idx))
        case _ =>
          container.appendChild(dom)
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
