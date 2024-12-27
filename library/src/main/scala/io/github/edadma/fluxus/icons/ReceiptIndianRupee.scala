package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-indian-rupee", JSImport.Default)
private object ReceiptIndianRupeeIcon extends js.Array[js.Any]

def ReceiptIndianRupee(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptIndianRupeeIcon, color, size)
