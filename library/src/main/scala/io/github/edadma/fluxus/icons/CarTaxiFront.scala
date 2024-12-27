package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/car-taxi-front", JSImport.Default)
private object CarTaxiFrontIcon extends js.Array[js.Any]

def CarTaxiFront(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CarTaxiFrontIcon, color, size)
