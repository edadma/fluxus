package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-equal", JSImport.Default)
private object SquareEqualIcon extends js.Array[js.Any]

def SquareEqual(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareEqualIcon, color, size)
