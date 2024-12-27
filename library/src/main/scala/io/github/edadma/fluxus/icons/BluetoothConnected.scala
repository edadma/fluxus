package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bluetooth-connected", JSImport.Default)
private object BluetoothConnectedIcon extends js.Array[js.Any]

def BluetoothConnected(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BluetoothConnectedIcon, color, size)
