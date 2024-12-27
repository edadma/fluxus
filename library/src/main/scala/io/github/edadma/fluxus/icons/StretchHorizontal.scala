package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/stretch-horizontal", JSImport.Default)
private object StretchHorizontalIcon extends js.Array[js.Any]

def StretchHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StretchHorizontalIcon, color, size)
