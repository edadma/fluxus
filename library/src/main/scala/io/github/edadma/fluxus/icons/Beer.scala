package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/beer", JSImport.Default)
private object BeerIcon extends js.Array[js.Any]

def Beer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BeerIcon, color, size)
