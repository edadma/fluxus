package io.github.edadma.fluxus.icons

import io.github.edadma.fluxus.RawNode

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import org.scalajs.dom

import scala.language.postfixOps

@js.native
@JSImport("lucide/dist/esm/createElement", JSImport.Default)
private object ucideCreateElement extends js.Function1[js.Array[js.Any], dom.Element]

private def createIcon(icon: js.Array[js.Any], color: String, size: Int): RawNode = {
  val iconCopy = js.Array[js.Any](
    icon(0),
    js.Dictionary(
      icon(1).asInstanceOf[js.Dictionary[js.Any]].toMap.toSeq*,
    ),
    icon(al attrs = iconCopy(1).asInstanceOf[js.Dictionary[js.Any]]
  attrs("stroke") = color
  attrs("width") = size
  attrs("height") = size

  RawNode(LucideCreateElement(iconCopy))
}
