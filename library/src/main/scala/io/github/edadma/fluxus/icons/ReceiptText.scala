package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-text", JSImport.Default)
private object ReceiptTextIcon extends js.Array[js.Any]

def ReceiptText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptTextIcon, color, size)
