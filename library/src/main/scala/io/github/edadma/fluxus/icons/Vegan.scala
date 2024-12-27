package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/vegan", JSImport.Default)
private object VeganIcon extends js.Array[js.Any]

def Vegan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VeganIcon, color, size)
