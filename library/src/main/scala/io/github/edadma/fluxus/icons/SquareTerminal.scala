package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-terminal", JSImport.Default)
private object SquareTerminalIcon extends js.Array[js.Any]

def SquareTerminal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareTerminalIcon, color, size)
