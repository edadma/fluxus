package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-end-horizontal", JSImport.Default)
private object AlignEndHorizontalIcon extends js.Array[js.Any]

def AlignEndHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignEndHorizontalIcon, color, size)
