package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pin-off", JSImport.Default)
private object PinOffIcon extends js.Array[js.Any]

def PinOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PinOffIcon, color, size)
