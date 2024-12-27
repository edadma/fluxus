package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-qr-code", JSImport.Default)
private object ScanQrCodeIcon extends js.Array[js.Any]

def ScanQrCode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanQrCodeIcon, color, size)
