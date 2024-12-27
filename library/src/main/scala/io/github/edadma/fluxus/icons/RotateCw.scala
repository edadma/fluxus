package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rotate-cw", JSImport.Default)
private object RotateCwIcon extends js.Array[js.Any]

def RotateCw(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RotateCwIcon, color, size)
