package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ambulance", JSImport.Default)
private object AmbulanceIcon extends js.Array[js.Any]

def Ambulance(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AmbulanceIcon, color, size)
