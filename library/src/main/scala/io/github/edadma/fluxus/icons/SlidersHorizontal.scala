package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sliders-horizontal", JSImport.Default)
private object SlidersHorizontalIcon extends js.Array[js.Any]

def SlidersHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SlidersHorizontalIcon, color, size)
