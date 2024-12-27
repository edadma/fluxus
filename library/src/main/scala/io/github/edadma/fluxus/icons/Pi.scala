package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pi", JSImport.Default)
private object PiIcon extends js.Array[js.Any]

def Pi(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PiIcon, color, size)
