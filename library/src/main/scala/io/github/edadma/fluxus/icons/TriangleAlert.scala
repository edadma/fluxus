package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/triangle-alert", JSImport.Default)
private object TriangleAlertIcon extends js.Array[js.Any]

def TriangleAlert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TriangleAlertIcon, color, size)
