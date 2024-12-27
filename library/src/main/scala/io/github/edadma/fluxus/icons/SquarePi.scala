package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-pi", JSImport.Default)
private object SquarePiIcon extends js.Array[js.Any]

def SquarePi(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquarePiIcon, color, size)
