package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/droplet-off", JSImport.Default)
private object DropletOffIcon extends js.Array[js.Any]

def DropletOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DropletOffIcon, color, size)
