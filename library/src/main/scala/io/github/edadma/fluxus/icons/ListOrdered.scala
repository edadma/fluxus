package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-ordered", JSImport.Default)
private object ListOrderedIcon extends js.Array[js.Any]

def ListOrdered(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListOrderedIcon, color, size)
