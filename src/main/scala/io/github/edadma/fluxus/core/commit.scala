package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.logger
import org.scalajs.dom
import DOMOperation._

def commit(op: DOMOperation, container: dom.Element): Unit = {
  logger.debug(
    "Committing DOM operation",
    category = "Reconciler",
    opId = 1,
    Map("operation" -> op.toString),
  )

  op match {
    case Replace(oldNode, newNode) =>
      oldNode.domNode.foreach { old =>
        val dom = createDOMNode(newNode)
        old.parentNode.replaceChild(dom, old)
      }

    case UpdateText(node, newText) =>
      node.domNode.foreach(_.textContent = newText)

    case UpdateProps(node, propsToRemove, propsToAdd) =>
      node.domNode.foreach { n =>
        val element = n.asInstanceOf[dom.Element]

        propsToRemove.foreach(element.removeAttribute)
        propsToAdd.foreach { case (name, value) =>
          element.setAttribute(name, value.toString)
        }
      }

    case RemoveNode(node) =>
      node.domNode.foreach(dom => dom.parentNode.removeChild(dom))

    case InsertNode(node, position) =>
      val dom = createDOMNode(node)
      position match {
        case Some(idx) if idx < container.childNodes.length =>
          container.insertBefore(dom, container.childNodes(idx))
        case _ =>
          container.appendChild(dom)
      }

    case RerenderComponent(old, newProps) =>
      val newRendered = newProps.component(newProps.props)
      old.domNode.foreach { n =>
        val parent = n.parentNode.asInstanceOf[dom.Element]
        val newDom = createDOMNode(newRendered)
        parent.replaceChild(newDom, n)
      }
  }
}
