package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-ban", JSImport.Default)
private object ShieldBanIcon extends js.Array[js.Any]

def ShieldBan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldBanIcon, color, size)
