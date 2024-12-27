package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tangent", JSImport.Default)
private object TangentIcon extends js.Array[js.Any]

def Tangent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TangentIcon, color, size)
