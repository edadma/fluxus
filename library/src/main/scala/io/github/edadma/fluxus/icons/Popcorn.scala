package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/popcorn", JSImport.Default)
private object PopcornIcon extends js.Array[js.Any]

def Popcorn(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PopcornIcon, color, size)
