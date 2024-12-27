package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bomb", JSImport.Default)
private object BombIcon extends js.Array[js.Any]

def Bomb(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BombIcon, color, size)
