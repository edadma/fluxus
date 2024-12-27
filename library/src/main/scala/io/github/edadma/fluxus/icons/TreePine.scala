package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tree-pine", JSImport.Default)
private object TreePineIcon extends js.Array[js.Any]

def TreePine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TreePineIcon, color, size)
