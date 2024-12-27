package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-chevron-right", JSImport.Default)
private object SquareChevronRightIcon extends js.Array[js.Any]

def SquareChevronRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareChevronRightIcon, color, size)
