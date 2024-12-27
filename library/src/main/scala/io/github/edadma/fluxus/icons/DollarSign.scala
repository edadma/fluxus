package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dollar-sign", JSImport.Default)
private object DollarSignIcon extends js.Array[js.Any]

def DollarSign(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DollarSignIcon, color, size)
