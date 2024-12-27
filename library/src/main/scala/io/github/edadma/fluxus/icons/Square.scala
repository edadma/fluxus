package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square", JSImport.Default)
private object SquareIcon extends js.Array[js.Any]

def Square(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareIcon, color, size)
