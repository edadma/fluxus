package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/receipt", JSImport.Default)
private object ReceiptIcon extends js.Array[js.Any]

def Receipt(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReceiptIcon, color, size)
