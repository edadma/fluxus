package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-alert", JSImport.Default)
private object BadgeAlertIcon extends js.Array[js.Any]

def BadgeAlert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeAlertIcon, color, size)
