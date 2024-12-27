package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-stack", JSImport.Default)
private object SquareStackIcon extends js.Array[js.Any]

def SquareStack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareStackIcon, color, size)
