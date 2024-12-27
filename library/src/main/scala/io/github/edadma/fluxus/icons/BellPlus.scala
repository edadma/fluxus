package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bell-plus", JSImport.Default)
private object BellPlusIcon extends js.Array[js.Any]

def BellPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BellPlusIcon, color, size)
