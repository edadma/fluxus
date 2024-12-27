package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/infinity", JSImport.Default)
private object InfinityIcon extends js.Array[js.Any]

def Infinity(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(InfinityIcon, color, size)
