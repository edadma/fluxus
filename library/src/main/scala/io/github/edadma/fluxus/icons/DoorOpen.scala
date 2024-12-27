package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/door-open", JSImport.Default)
private object DoorOpenIcon extends js.Array[js.Any]

def DoorOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DoorOpenIcon, color, size)
