package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/stamp", JSImport.Default)
private object StampIcon extends js.Array[js.Any]

def Stamp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StampIcon, color, size)
