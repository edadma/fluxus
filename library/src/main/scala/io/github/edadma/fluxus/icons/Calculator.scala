package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calculator", JSImport.Default)
private object CalculatorIcon extends js.Array[js.Any]

def Calculator(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalculatorIcon, color, size)
