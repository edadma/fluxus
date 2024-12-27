package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lamp-floor", JSImport.Default)
private object LampFloorIcon extends js.Array[js.Any]

def LampFloor(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LampFloorIcon, color, size)
