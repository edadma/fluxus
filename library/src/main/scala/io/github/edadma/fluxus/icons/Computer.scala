package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/computer", JSImport.Default)
private object ComputerIcon extends js.Array[js.Any]

def Computer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ComputerIcon, color, size)
