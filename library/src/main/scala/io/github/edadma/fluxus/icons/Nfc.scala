package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/nfc", JSImport.Default)
private object NfcIcon extends js.Array[js.Any]

def Nfc(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NfcIcon, color, size)
