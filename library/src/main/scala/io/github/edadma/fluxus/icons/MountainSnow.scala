package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mountain-snow", JSImport.Default)
private object MountainSnowIcon extends js.Array[js.Any]

def MountainSnow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MountainSnowIcon, color, size)
