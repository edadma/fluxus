package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/quote", JSImport.Default)
private object QuoteIcon extends js.Array[js.Any]

def Quote(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(QuoteIcon, color, size)
