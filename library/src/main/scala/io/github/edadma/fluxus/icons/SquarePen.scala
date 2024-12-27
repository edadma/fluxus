package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-pen", JSImport.Default)
private object SquarePenIcon extends js.Array[js.Any]

def SquarePen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquarePenIcon, color, size)
