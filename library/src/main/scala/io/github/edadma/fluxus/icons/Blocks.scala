package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/blocks", JSImport.Default)
private object BlocksIcon extends js.Array[js.Any]

def Blocks(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BlocksIcon, color, size)
