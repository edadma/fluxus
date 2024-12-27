package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-ellipsis", JSImport.Default)
private object CircleEllipsisIcon extends js.Array[js.Any]

def CircleEllipsis(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleEllipsisIcon, color, size)
