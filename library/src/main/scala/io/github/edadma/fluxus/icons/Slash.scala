package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/slash", JSImport.Default)
private object SlashIcon extends js.Array[js.Any]

def Slash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SlashIcon, color, size)
