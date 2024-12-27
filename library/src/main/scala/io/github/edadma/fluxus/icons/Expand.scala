package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/expand", JSImport.Default)
private object ExpandIcon extends js.Array[js.Any]

def Expand(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ExpandIcon, color, size)
