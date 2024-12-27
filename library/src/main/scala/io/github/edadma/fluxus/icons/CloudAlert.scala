package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-alert", JSImport.Default)
private object CloudAlertIcon extends js.Array[js.Any]

def CloudAlert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudAlertIcon, color, size)
