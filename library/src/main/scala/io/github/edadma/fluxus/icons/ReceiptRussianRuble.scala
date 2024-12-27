package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-russian-ruble", JSImport.Default)
private object ReceiptRussianRubleIcon extends js.Array[js.Any]

def ReceiptRussianRuble(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptRussianRubleIcon, color, size)
