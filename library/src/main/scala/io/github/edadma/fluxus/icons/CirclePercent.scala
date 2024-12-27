package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-percent", JSImport.Default)
private object CirclePercentIcon extends js.Array[js.Any]

def CirclePercent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CirclePercentIcon, color, size)
