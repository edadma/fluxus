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
      val rendered = ComponentInstance.withInstance(old.instance.get) {
        newProps.component(newProps.props)
      }

      old.instance.foreach(_.rendered = Some(rendered))
      old.domNode.foreach { n =>
        val parent = n.parentNode.asInstanceOf[dom.Element]
        val newDom = createDOMNode(rendered)
        parent.replaceChild(newDom, n)
      }

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
