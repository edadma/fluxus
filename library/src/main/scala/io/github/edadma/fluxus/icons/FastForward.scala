package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fast-forward", JSImport.Default)
private object FastForwardIcon extends js.Array[js.Any]

def FastForward(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FastForwardIcon, color, size)
