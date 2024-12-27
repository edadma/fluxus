package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/spade", JSImport.Default)
private object SpadeIcon extends js.Array[js.Any]

def Spade(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SpadeIcon, color, size)
