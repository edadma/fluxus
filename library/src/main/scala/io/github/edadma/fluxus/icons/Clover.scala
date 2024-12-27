package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clover", JSImport.Default)
private object CloverIcon extends js.Array[js.Any]

def Clover(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloverIcon, color, size)
