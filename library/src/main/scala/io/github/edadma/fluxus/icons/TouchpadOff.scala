package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/touchpad-off", JSImport.Default)
private object TouchpadOffIcon extends js.Array[js.Any]

def TouchpadOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TouchpadOffIcon, color, size)
