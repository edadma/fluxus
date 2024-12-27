package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-asterisk", JSImport.Default)
private object SquareAsteriskIcon extends js.Array[js.Any]

def SquareAsterisk(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareAsteriskIcon, color, size)
