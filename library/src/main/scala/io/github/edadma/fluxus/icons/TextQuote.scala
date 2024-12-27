package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/text-quote", JSImport.Default)
private object TextQuoteIcon extends js.Array[js.Any]

def TextQuote(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TextQuoteIcon, color, size)
