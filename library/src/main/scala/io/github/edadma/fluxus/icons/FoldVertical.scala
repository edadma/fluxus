package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fold-vertical", JSImport.Default)
private object FoldVerticalIcon extends js.Array[js.Any]

def FoldVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FoldVerticalIcon, color, size)
