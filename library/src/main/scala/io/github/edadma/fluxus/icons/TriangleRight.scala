package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/triangle-right", JSImport.Default)
private object TriangleRightIcon extends js.Array[js.Any]

def TriangleRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TriangleRightIcon, color, size)
