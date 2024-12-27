package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-check", JSImport.Default)
private object ShieldCheckIcon extends js.Array[js.Any]

def ShieldCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldCheckIcon, color, size)
