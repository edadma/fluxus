package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flag-triangle-left", JSImport.Default)
private object FlagTriangleLeftIcon extends js.Array[js.Any]

def FlagTriangleLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlagTriangleLeftIcon, color, size)
