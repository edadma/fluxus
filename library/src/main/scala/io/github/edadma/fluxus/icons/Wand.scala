package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wand", JSImport.Default)
private object WandIcon extends js.Array[js.Any]

def Wand(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WandIcon, color, size)
