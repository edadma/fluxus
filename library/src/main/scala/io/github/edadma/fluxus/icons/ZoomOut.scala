package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/zoom-out", JSImport.Default)
private object ZoomOutIcon extends js.Array[js.Any]

def ZoomOut(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ZoomOutIcon, color, size)
