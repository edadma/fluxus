package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/power", JSImport.Default)
private object PowerIcon extends js.Array[js.Any]

def Power(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PowerIcon, color, size)
