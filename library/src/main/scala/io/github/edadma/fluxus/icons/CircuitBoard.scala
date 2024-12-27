package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circuit-board", JSImport.Default)
private object CircuitBoardIcon extends js.Array[js.Any]

def CircuitBoard(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircuitBoardIcon, color, size)
