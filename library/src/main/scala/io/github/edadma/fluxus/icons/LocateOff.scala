package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/locate-off", JSImport.Default)
private object LocateOffIcon extends js.Array[js.Any]

def LocateOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LocateOffIcon, color, size)
