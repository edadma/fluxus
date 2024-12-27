package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-alert", JSImport.Default)
private object ShieldAlertIcon extends js.Array[js.Any]

def ShieldAlert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldAlertIcon, color, size)
