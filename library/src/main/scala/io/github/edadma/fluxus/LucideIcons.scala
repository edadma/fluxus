package io.github.edadma.fluxus

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("lucide", JSImport.Namespace)
object LucideIcons extends js.Object {
  // Each icon is an array: [tag, attributes, children]
  // We'll type it more precisely to help with conversion
  type IconElement = js.Array[js.Any]

  // Import specific icons we want
  val Camera: IconElement    = js.native
  val CircleDot: IconElement = js.native
  // etc...
}

def Icon(iconArray: LucideIcons.IconElement)(props: (String, Any)*): RawNode = {
  // Create SVG element
  val element = dom.document.createElementNS("http://www.w3.org/2000/svg", "svg")

  logger.debug(
    "Creating icon from array",
    category = "Icons",
    Map(
      "arrayLength" -> iconArray.length.toString,
      "props"       -> props.toString,
    ),
  )

  // Process attributes from default attributes (second array element)
  val defaultAttrs = iconArray(1).asInstanceOf[js.Dictionary[String]]
  val userProps    = props.toMap

  // Set attributes, overriding defaults with user props
  defaultAttrs.foreach { case (key, value) =>
    element.setAttribute(key, value)
  }

  // Apply user props
  userProps.foreach { case (key, value) =>
    if (key == "size") {
      element.setAttribute("width", value.toString)
      element.setAttribute("height", value.toString)
    } else {
      element.setAttribute(key, value.toString)
    }
  }

  // Process children (third array element)
  val children = iconArray(2).asInstanceOf[js.Array[js.Array[Any]]]

  // Helper to create child SVG elements
  def createChild(childArr: js.Array[Any]): dom.Element = {
    val tag          = childArr(0).asInstanceOf[String]
    val attrs        = childArr(1).asInstanceOf[js.Dictionary[String]]
    val childElement = dom.document.createElementNS("http://www.w3.org/2000/svg", tag)

    // Apply attributes
    attrs.foreach { case (key, value) =>
      childElement.setAttribute(key, value)
    }

    childElement
  }

  // Create and append all children
  children.foreach { childArr =>
    element.appendChild(createChild(childArr))
  }

  RawNode(element)
}
