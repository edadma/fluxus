package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/barcode", JSImport.Default)
private object BarcodeIcon extends js.Array[js.Any]

def Barcode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BarcodeIcon, color, size)
