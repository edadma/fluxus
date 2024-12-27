package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/qr-code", JSImport.Default)
private object QrCodeIcon extends js.Array[js.Any]

def QrCode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(QrCodeIcon, color, size)
