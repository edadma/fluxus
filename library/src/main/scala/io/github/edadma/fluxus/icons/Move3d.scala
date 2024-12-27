package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-3d", JSImport.Default)
private object Move3dIcon extends js.Array[js.Any]

def Move3d(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Move3dIcon, color, size)
