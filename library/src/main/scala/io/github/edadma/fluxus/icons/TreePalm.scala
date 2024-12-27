package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tree-palm", JSImport.Default)
private object TreePalmIcon extends js.Array[js.Any]

def TreePalm(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TreePalmIcon, color, size)
