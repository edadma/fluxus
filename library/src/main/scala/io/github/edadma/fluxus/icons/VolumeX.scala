package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/volume-x", JSImport.Default)
private object VolumeXIcon extends js.Array[js.Any]

def VolumeX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VolumeXIcon, color, size)
