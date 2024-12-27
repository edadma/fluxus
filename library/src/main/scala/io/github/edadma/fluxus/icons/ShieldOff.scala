package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-off", JSImport.Default)
private object ShieldOffIcon extends js.Array[js.Any]

def ShieldOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldOffIcon, color, size)
