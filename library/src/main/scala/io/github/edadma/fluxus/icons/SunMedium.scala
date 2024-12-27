package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sun-medium", JSImport.Default)
private object SunMediumIcon extends js.Array[js.Any]

def SunMedium(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SunMediumIcon, color, size)
