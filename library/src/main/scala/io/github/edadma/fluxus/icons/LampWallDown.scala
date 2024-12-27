package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lamp-wall-down", JSImport.Default)
private object LampWallDownIcon extends js.Array[js.Any]

def LampWallDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LampWallDownIcon, color, size)
