package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-parking-off", JSImport.Default)
private object CircleParkingOffIcon extends js.Array[js.Any]

def CircleParkingOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleParkingOffIcon, color, size)
