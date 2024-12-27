package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bus", JSImport.Default)
private object BusIcon extends js.Array[js.Any]

def Bus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BusIcon, color, size)
