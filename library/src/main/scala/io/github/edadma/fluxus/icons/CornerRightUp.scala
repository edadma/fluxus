package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-right-up", JSImport.Default)
private object CornerRightUpIcon extends js.Array[js.Any]

def CornerRightUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerRightUpIcon, color, size)
