package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-dollar-sign", JSImport.Default)
private object CircleDollarSignIcon extends js.Array[js.Any]

def CircleDollarSign(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleDollarSignIcon, color, size)
