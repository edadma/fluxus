package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/terminal", JSImport.Default)
private object TerminalIcon extends js.Array[js.Any]

def Terminal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TerminalIcon, color, size)
