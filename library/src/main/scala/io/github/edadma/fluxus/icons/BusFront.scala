package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bus-front", JSImport.Default)
private object BusFrontIcon extends js.Array[js.Any]

def BusFront(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BusFrontIcon, color, size)
