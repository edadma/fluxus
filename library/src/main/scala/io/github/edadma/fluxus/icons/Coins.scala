package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/coins", JSImport.Default)
private object CoinsIcon extends js.Array[js.Any]

def Coins(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CoinsIcon, color, size)
