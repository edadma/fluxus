package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shovel", JSImport.Default)
private object ShovelIcon extends js.Array[js.Any]

def Shovel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShovelIcon, color, size)
