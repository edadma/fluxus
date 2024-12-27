package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/brick-wall", JSImport.Default)
private object BrickWallIcon extends js.Array[js.Any]

def BrickWall(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BrickWallIcon, color, size)
