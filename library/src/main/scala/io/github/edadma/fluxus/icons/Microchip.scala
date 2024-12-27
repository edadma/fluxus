package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/microchip", JSImport.Default)
private object MicrochipIcon extends js.Array[js.Any]

def Microchip(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MicrochipIcon, color, size)
