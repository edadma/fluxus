package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/touchpad", JSImport.Default)
private object TouchpadIcon extends js.Array[js.Any]

def Touchpad(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TouchpadIcon, color, size)
