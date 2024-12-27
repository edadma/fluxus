package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sun-moon", JSImport.Default)
private object SunMoonIcon extends js.Array[js.Any]

def SunMoon(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SunMoonIcon, color, size)
