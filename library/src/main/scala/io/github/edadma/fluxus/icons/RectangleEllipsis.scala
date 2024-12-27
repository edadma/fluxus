package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rectangle-ellipsis", JSImport.Default)
private object RectangleEllipsisIcon extends js.Array[js.Any]

def RectangleEllipsis(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RectangleEllipsisIcon, color, size)
