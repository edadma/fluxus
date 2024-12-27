package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/volume-off", JSImport.Default)
private object VolumeOffIcon extends js.Array[js.Any]

def VolumeOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VolumeOffIcon, color, size)
