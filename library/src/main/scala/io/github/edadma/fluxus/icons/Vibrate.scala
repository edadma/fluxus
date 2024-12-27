package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/vibrate", JSImport.Default)
private object VibrateIcon extends js.Array[js.Any]

def Vibrate(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VibrateIcon, color, size)
