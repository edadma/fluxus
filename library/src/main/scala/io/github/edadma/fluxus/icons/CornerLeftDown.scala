package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-left-down", JSImport.Default)
private object CornerLeftDownIcon extends js.Array[js.Any]

def CornerLeftDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerLeftDownIcon, color, size)
