package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt-swiss-franc", JSImport.Default)
private object ReceiptSwissFrancIcon extends js.Array[js.Any]

def ReceiptSwissFranc(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptSwissFrancIcon, color, size)
