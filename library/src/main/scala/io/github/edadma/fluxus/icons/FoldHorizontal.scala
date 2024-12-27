package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fold-horizontal", JSImport.Default)
private object FoldHorizontalIcon extends js.Array[js.Any]

def FoldHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FoldHorizontalIcon, color, size)
