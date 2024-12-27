package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-slash", JSImport.Default)
private object SquareSlashIcon extends js.Array[js.Any]

def SquareSlash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareSlashIcon, color, size)
