package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/washing-machine", JSImport.Default)
private object WashingMachineIcon extends js.Array[js.Any]

def WashingMachine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WashingMachineIcon, color, size)
