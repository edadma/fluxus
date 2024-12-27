package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-left-up", JSImport.Default)
private object CornerLeftUpIcon extends js.Array[js.Any]

def CornerLeftUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerLeftUpIcon, color, size)
