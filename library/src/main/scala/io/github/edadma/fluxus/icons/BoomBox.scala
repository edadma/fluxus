package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/boom-box", JSImport.Default)
private object BoomBoxIcon extends js.Array[js.Any]

def BoomBox(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BoomBoxIcon, color, size)
