package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fish-symbol", JSImport.Default)
private object FishSymbolIcon extends js.Array[js.Any]

def FishSymbol(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FishSymbolIcon, color, size)
