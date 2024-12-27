package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/key-square", JSImport.Default)
private object KeySquareIcon extends js.Array[js.Any]

def KeySquare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(KeySquareIcon, color, size)
