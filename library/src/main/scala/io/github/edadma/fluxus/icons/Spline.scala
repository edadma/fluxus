package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/spline", JSImport.Default)
private object SplineIcon extends js.Array[js.Any]

def Spline(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SplineIcon, color, size)
