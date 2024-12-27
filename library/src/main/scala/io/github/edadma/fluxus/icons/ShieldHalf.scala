package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-half", JSImport.Default)
private object ShieldHalfIcon extends js.Array[js.Any]

def ShieldHalf(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldHalfIcon, color, size)
