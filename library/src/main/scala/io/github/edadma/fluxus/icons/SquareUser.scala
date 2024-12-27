package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-user", JSImport.Default)
private object SquareUserIcon extends js.Array[js.Any]

def SquareUser(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareUserIcon, color, size)
