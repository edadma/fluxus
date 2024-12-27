package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-spline", JSImport.Default)
private object ChartSplineIcon extends js.Array[js.Any]

def ChartSpline(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartSplineIcon, color, size)
