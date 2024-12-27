package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/vibrate-off", JSImport.Default)
private object VibrateOffIcon extends js.Array[js.Any]

def VibrateOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VibrateOffIcon, color, size)
