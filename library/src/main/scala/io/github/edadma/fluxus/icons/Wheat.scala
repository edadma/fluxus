package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wheat", JSImport.Default)
private object WheatIcon extends js.Array[js.Any]

def Wheat(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WheatIcon, color, size)
