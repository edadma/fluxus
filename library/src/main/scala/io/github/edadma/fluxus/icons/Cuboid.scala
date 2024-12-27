package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cuboid", JSImport.Default)
private object CuboidIcon extends js.Array[js.Any]

def Cuboid(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CuboidIcon, color, size)
