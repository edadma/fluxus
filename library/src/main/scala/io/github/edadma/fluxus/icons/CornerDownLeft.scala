package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-down-left", JSImport.Default)
private object CornerDownLeftIcon extends js.Array[js.Any]

def CornerDownLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerDownLeftIcon, color, size)
