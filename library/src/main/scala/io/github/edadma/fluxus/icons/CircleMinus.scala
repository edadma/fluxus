package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-minus", JSImport.Default)
private object CircleMinusIcon extends js.Array[js.Any]

def CircleMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleMinusIcon, color, size)
