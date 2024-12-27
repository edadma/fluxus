package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-parking", JSImport.Default)
private object SquareParkingIcon extends js.Array[js.Any]

def SquareParking(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareParkingIcon, color, size)
