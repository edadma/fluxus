package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/divide", JSImport.Default)
private object DivideIcon extends js.Array[js.Any]

def Divide(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DivideIcon, color, size)
