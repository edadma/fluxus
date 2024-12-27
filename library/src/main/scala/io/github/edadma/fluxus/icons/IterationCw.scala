package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/iteration-cw", JSImport.Default)
private object IterationCwIcon extends js.Array[js.Any]

def IterationCw(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(IterationCwIcon, color, size)
