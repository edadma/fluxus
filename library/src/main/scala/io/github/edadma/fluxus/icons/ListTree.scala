package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-tree", JSImport.Default)
private object ListTreeIcon extends js.Array[js.Any]

def ListTree(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListTreeIcon, color, size)
