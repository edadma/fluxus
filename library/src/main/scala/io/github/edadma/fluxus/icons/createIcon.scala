package io.github.edadma.fluxus.icons

import io.github.edadma.fluxus.RawNode
import org.scalajs.dom

import scala.language.postfixOps
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("lucide/dist/esm/createElement", JSImport.Default)
private object lucideCreateElement extends js.Object {
  def apply(arr: js.Array[js.Any]): dom.Element = js.native
}

private[icons] def createIcon(icon: js.Array[js.Any], color: String, size: Int): RawNode = {
  val iconCopy = js.Array[js.Any](
    icon(0),
    js.Dictionary(
      icon(1).asInstanceOf[js.Dictionary[js.Any]].toMap.toSeq*,
    ),
    icon(2),
  )

  val attrs = iconCopy(1).asInstanceOf[js.Dictionary[js.Any]]

  attrs("stroke") = color
  attrs("width") = size
  attrs("height") = size

  RawNode(lucideCreateElement(iconCopy))
}
