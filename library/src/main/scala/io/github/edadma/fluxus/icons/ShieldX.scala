package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-x", JSImport.Default)
private object ShieldXIcon extends js.Array[js.Any]

def ShieldX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldXIcon, color, size)
