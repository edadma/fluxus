package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/skip-forward", JSImport.Default)
private object SkipForwardIcon extends js.Array[js.Any]

def SkipForward(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SkipForwardIcon, color, size)
