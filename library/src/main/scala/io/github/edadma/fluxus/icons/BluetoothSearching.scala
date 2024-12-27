package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bluetooth-searching", JSImport.Default)
private object BluetoothSearchingIcon extends js.Array[js.Any]

def BluetoothSearching(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BluetoothSearchingIcon, color, size)
