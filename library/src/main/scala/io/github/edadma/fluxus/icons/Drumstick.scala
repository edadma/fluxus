package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/drumstick", JSImport.Default)
private object DrumstickIcon extends js.Array[js.Any]

def Drumstick(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DrumstickIcon, color, size)
