package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-rain", JSImport.Default)
private object CloudRainIcon extends js.Array[js.Any]

def CloudRain(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudRainIcon, color, size)
