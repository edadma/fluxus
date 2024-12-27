package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-cent", JSImport.Default)
private object ReceiptCentIcon extends js.Array[js.Any]

def ReceiptCent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptCentIcon, color, size)
