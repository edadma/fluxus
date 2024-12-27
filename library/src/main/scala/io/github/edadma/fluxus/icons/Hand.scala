package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hand", JSImport.Default)
private object HandIcon extends js.Array[js.Any]

def Hand(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HandIcon, color, size)
