package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-play", JSImport.Default)
private object SquarePlayIcon extends js.Array[js.Any]

def SquarePlay(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquarePlayIcon, color, size)
