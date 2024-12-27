package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hand-metal", JSImport.Default)
private object HandMetalIcon extends js.Array[js.Any]

def HandMetal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HandMetalIcon, color, size)
