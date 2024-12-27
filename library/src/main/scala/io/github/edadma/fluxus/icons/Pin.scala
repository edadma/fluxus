package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pin", JSImport.Default)
private object PinIcon extends js.Array[js.Any]

def Pin(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PinIcon, color, size)
