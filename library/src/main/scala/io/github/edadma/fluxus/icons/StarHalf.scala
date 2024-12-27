package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/star-half", JSImport.Default)
private object StarHalfIcon extends js.Array[js.Any]

def StarHalf(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StarHalfIcon, color, size)
