package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flashlight-off", JSImport.Default)
private object FlashlightOffIcon extends js.Array[js.Any]

def FlashlightOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlashlightOffIcon, color, size)