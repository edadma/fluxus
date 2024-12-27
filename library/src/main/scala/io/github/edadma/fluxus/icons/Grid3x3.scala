package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/grid-3x3", JSImport.Default)
private object Grid3x3Icon extends js.Array[js.Any]

def Grid3x3(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Grid3x3Icon, color, size)
