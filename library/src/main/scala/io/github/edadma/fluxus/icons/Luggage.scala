package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/luggage", JSImport.Default)
private object LuggageIcon extends js.Array[js.Any]

def Luggage(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LuggageIcon, color, size)
