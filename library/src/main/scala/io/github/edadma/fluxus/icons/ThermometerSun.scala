package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/thermometer-sun", JSImport.Default)
private object ThermometerSunIcon extends js.Array[js.Any]

def ThermometerSun(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ThermometerSunIcon, color, size)
