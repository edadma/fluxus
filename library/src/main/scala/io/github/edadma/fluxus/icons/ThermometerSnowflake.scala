package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/thermometer-snowflake", JSImport.Default)
private object ThermometerSnowflakeIcon extends js.Array[js.Any]

def ThermometerSnowflake(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ThermometerSnowflakeIcon, color, size)
