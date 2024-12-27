package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/nut-off", JSImport.Default)
private object NutOffIcon extends js.Array[js.Any]

def NutOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NutOffIcon, color, size)
