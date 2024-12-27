package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/unfold-vertical", JSImport.Default)
private object UnfoldVerticalIcon extends js.Array[js.Any]

def UnfoldVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UnfoldVerticalIcon, color, size)
