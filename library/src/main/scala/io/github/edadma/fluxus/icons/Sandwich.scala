package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sandwich", JSImport.Default)
private object SandwichIcon extends js.Array[js.Any]

def Sandwich(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SandwichIcon, color, size)
