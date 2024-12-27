package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-gauge", JSImport.Default)
private object CircleGaugeIcon extends js.Array[js.Any]

def CircleGauge(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleGaugeIcon, color, size)
