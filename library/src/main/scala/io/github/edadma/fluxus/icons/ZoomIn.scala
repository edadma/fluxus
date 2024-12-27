package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/zoom-in", JSImport.Default)
private object ZoomInIcon extends js.Array[js.Any]

def ZoomIn(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ZoomInIcon, color, size)
