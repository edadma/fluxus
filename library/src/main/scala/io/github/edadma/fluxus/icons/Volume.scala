package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/volume", JSImport.Default)
private object VolumeIcon extends js.Array[js.Any]

def Volume(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VolumeIcon, color, size)
