package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sun-dim", JSImport.Default)
private object SunDimIcon extends js.Array[js.Any]

def SunDim(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SunDimIcon, color, size)
