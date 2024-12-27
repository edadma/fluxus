package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trees", JSImport.Default)
private object TreesIcon extends js.Array[js.Any]

def Trees(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TreesIcon, color, size)
