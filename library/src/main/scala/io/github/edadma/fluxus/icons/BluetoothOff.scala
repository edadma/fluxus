package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bluetooth-off", JSImport.Default)
private object BluetoothOffIcon extends js.Array[js.Any]

def BluetoothOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BluetoothOffIcon, color, size)
