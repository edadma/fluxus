package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heater", JSImport.Default)
private object HeaterIcon extends js.Array[js.Any]

def Heater(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeaterIcon, color, size)
