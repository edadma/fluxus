package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hotel", JSImport.Default)
private object HotelIcon extends js.Array[js.Any]

def Hotel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HotelIcon, color, size)
