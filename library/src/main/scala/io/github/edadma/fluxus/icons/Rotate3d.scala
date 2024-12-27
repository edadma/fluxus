package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rotate-3d", JSImport.Default)
private object Rotate3dIcon extends js.Array[js.Any]

def Rotate3d(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Rotate3dIcon, color, size)
