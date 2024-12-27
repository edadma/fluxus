package io.github.edadma.fluxus

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

// Import Camera icon array definition
type IconElement = js.Array[js.Any]

@js.native
@JSImport("lucide/dist/esm/icons/camera", JSImport.Default)
object Camera extends IconElement

@js.native
@JSImport("lucide/dist/esm/icons/circle-dot", JSImport.Default)
object CircleDot extends IconElement

object Icon {
  def apply(iconArray: IconElement)(props: (String, Any)*): RawNode = {
    val element = dom.document.createElementNS("http://www.w3.org/2000/svg", "svg")

    logger.debug(
      "Creating icon from array",
      category = "Icons",
      Map(
        "arrayLength"  -> iconArray.length.toString,
        "props"        -> props.toString,
        "defaultAttrs" -> iconArray(1).toString,
      ),
    )

    // Process attributes from default attributes (second array element)
    val defaultAttrs = iconArray(1).asInstanceOf[js.Dictionary[js.Any]]
    val userProps    = props.toMap

    // Set attributes, converting values to strings
    defaultAttrs.foreach { case (key, value) =>
      element.setAttribute(key, value.toString)
    }

    // Apply user props with special handling for size and color
    userProps.foreach { case (key, value) =>
      key match {
        case "size" =>
          element.setAttribute("width", value.toString)
          element.setAttribute("height", value.toString)
        case "color" =>
          element.setAttribute("stroke", value.toString)
        case _ =>
          element.setAttribute(key, value.toString)
      }
    }

    // Process children (third array element)
    val children = iconArray(2).asInstanceOf[js.Array[js.Array[Any]]]

    def createChild(childArr: js.Array[Any]): dom.Element = {
      val tag          = childArr(0).asInstanceOf[String]
      val attrs        = childArr(1).asInstanceOf[js.Dictionary[js.Any]]
      val childElement = dom.document.createElementNS("http://www.w3.org/2000/svg", tag)

      attrs.foreach { case (key, value) =>
        childElement.setAttribute(key, value.toString)
      }

      childElement
    }

    children.foreach { childArr =>
      element.appendChild(createChild(childArr))
    }

    RawNode(element)
  }
}
