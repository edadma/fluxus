package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-sun-rain", JSImport.Default)
private object CloudSunRainIcon extends js.Array[js.Any]

def CloudSunRain(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudSunRainIcon, color, size)
