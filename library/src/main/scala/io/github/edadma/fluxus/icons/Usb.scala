package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/usb", JSImport.Default)
private object UsbIcon extends js.Array[js.Any]

def Usb(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UsbIcon, color, size)
