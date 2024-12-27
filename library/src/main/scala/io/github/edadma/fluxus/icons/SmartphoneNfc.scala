package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/smartphone-nfc", JSImport.Default)
private object SmartphoneNfcIcon extends js.Array[js.Any]

def SmartphoneNfc(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SmartphoneNfcIcon, color, size)
