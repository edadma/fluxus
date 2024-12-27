package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/power-off", JSImport.Default)
private object PowerOffIcon extends js.Array[js.Any]

def PowerOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PowerOffIcon, color, size)
