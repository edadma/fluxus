package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-alert", JSImport.Default)
private object CircleAlertIcon extends js.Array[js.Any]

def CircleAlert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleAlertIcon, color, size)
