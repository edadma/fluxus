package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-power", JSImport.Default)
private object CirclePowerIcon extends js.Array[js.Any]

def CirclePower(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CirclePowerIcon, color, size)
