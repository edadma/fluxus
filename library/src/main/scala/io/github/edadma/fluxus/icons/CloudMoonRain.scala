package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-moon-rain", JSImport.Default)
private object CloudMoonRainIcon extends js.Array[js.Any]

def CloudMoonRain(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudMoonRainIcon, color, size)
