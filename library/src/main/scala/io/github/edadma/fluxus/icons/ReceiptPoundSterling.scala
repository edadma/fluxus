package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-pound-sterling", JSImport.Default)
private object ReceiptPoundSterlingIcon extends js.Array[js.Any]

def ReceiptPoundSterling(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptPoundSterlingIcon, color, size)
