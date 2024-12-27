package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-barcode", JSImport.Default)
private object ScanBarcodeIcon extends js.Array[js.Any]

def ScanBarcode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanBarcodeIcon, color, size)
