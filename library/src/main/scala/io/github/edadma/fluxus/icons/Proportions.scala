package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/proportions", JSImport.Default)
private object ProportionsIcon extends js.Array[js.Any]

def Proportions(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ProportionsIcon, color, size)
