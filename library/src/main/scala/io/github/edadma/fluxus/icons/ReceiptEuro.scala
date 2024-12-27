package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-euro", JSImport.Default)
private object ReceiptEuroIcon extends js.Array[js.Any]

def ReceiptEuro(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptEuroIcon, color, size)
