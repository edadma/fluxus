package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/axis-3d", JSImport.Default)
private object Axis3dIcon extends js.Array[js.Any]

def Axis3d(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Axis3dIcon, color, size)
