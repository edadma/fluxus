package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-japanese-yen", JSImport.Default)
private object ReceiptJapaneseYenIcon extends js.Array[js.Any]

def ReceiptJapaneseYen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptJapaneseYenIcon, color, size)
