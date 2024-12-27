package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-alert", JSImport.Default)
private object ClockAlertIcon extends js.Array[js.Any]

def ClockAlert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClockAlertIcon, color, size)
