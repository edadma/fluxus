package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tent-tree", JSImport.Default)
private object TentTreeIcon extends js.Array[js.Any]

def TentTree(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TentTreeIcon, color, size)
