package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/iteration-ccw", JSImport.Default)
private object IterationCcwIcon extends js.Array[js.Any]

def IterationCcw(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(IterationCcwIcon, color, size)
