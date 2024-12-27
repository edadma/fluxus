package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/car-front", JSImport.Default)
private object CarFrontIcon extends js.Array[js.Any]

def CarFront(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CarFrontIcon, color, size)
