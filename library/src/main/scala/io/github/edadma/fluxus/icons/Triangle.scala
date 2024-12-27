package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/triangle", JSImport.Default)
private object TriangleIcon extends js.Array[js.Any]

def Triangle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TriangleIcon, color, size)
