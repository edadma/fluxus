package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ship", JSImport.Default)
private object ShipIcon extends js.Array[js.Any]

def Ship(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShipIcon, color, size)
