package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-rain-wind", JSImport.Default)
private object CloudRainWindIcon extends js.Array[js.Any]

def CloudRainWind(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudRainWindIcon, color, size)
