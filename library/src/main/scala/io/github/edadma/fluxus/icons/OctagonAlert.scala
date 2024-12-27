package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/octagon-alert", JSImport.Default)
private object OctagonAlertIcon extends js.Array[js.Any]

def OctagonAlert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OctagonAlertIcon, color, size)
