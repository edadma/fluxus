package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cake", JSImport.Default)
private object CakeIcon extends js.Array[js.Any]

def Cake(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CakeIcon, color, size)
