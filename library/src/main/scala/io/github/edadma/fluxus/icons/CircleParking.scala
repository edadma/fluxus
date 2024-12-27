package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-parking", JSImport.Default)
private object CircleParkingIcon extends js.Array[js.Any]

def CircleParking(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleParkingIcon, color, size)
