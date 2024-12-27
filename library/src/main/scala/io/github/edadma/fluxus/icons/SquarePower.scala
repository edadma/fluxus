package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-power", JSImport.Default)
private object SquarePowerIcon extends js.Array[js.Any]

def SquarePower(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquarePowerIcon, color, size)
