package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mic-off", JSImport.Default)
private object MicOffIcon extends js.Array[js.Any]

def MicOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MicOffIcon, color, size)
