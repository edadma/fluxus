package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-dollar-sign", JSImport.Default)
private object BadgeDollarSignIcon extends js.Array[js.Any]

def BadgeDollarSign(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeDollarSignIcon, color, size)
