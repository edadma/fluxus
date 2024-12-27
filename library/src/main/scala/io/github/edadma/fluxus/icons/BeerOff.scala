package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/beer-off", JSImport.Default)
private object BeerOffIcon extends js.Array[js.Any]

def BeerOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BeerOffIcon, color, size)
