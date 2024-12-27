package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/split", JSImport.Default)
private object SplitIcon extends js.Array[js.Any]

def Split(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SplitIcon, color, size)
