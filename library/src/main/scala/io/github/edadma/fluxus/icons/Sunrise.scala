package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sunrise", JSImport.Default)
private object SunriseIcon extends js.Array[js.Any]

def Sunrise(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SunriseIcon, color, size)
