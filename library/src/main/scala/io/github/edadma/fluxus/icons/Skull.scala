package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/skull", JSImport.Default)
private object SkullIcon extends js.Array[js.Any]

def Skull(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SkullIcon, color, size)
