package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-chevron-down", JSImport.Default)
private object SquareChevronDownIcon extends js.Array[js.Any]

def SquareChevronDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareChevronDownIcon, color, size)
