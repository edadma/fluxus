package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/currency", JSImport.Default)
private object CurrencyIcon extends js.Array[js.Any]

def Currency(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CurrencyIcon, color, size)
