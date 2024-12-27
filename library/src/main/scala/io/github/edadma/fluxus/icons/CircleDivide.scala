package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-divide", JSImport.Default)
private object CircleDivideIcon extends js.Array[js.Any]

def CircleDivide(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleDivideIcon, color, size)
