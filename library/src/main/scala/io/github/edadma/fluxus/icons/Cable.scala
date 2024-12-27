package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cable", JSImport.Default)
private object CableIcon extends js.Array[js.Any]

def Cable(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CableIcon, color, size)
