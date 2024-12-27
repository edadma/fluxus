package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rail-symbol", JSImport.Default)
private object RailSymbolIcon extends js.Array[js.Any]

def RailSymbol(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RailSymbolIcon, color, size)
