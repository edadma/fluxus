package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/unfold-horizontal", JSImport.Default)
private object UnfoldHorizontalIcon extends js.Array[js.Any]

def UnfoldHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UnfoldHorizontalIcon, color, size)
