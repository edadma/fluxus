package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-parking-off", JSImport.Default)
private object SquareParkingOffIcon extends js.Array[js.Any]

def SquareParkingOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareParkingOffIcon, color, size)
