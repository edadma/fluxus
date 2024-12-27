package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flag-triangle-right", JSImport.Default)
private object FlagTriangleRightIcon extends js.Array[js.Any]

def FlagTriangleRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlagTriangleRightIcon, color, size)
