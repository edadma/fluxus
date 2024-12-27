package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/forward", JSImport.Default)
private object ForwardIcon extends js.Array[js.Any]

def Forward(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ForwardIcon, color, size)
