package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-quote", JSImport.Default)
private object MessageSquareQuoteIcon extends js.Array[js.Any]

def MessageSquareQuote(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareQuoteIcon, color, size)
