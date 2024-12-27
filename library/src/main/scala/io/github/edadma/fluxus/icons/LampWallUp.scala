package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lamp-wall-up", JSImport.Default)
private object LampWallUpIcon extends js.Array[js.Any]

def LampWallUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LampWallUpIcon, color, size)
