package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/moon-star", JSImport.Default)
private object MoonStarIcon extends js.Array[js.Any]

def MoonStar(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoonStarIcon, color, size)
