package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-chevron-left", JSImport.Default)
private object SquareChevronLeftIcon extends js.Array[js.Any]

def SquareChevronLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareChevronLeftIcon, color, size)
