package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tree-deciduous", JSImport.Default)
private object TreeDeciduousIcon extends js.Array[js.Any]

def TreeDeciduous(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TreeDeciduousIcon, color, size)
