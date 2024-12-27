package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sliders-vertical", JSImport.Default)
private object SlidersVerticalIcon extends js.Array[js.Any]

def SlidersVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SlidersVerticalIcon, color, size)
