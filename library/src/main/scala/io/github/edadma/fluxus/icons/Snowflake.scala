package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/snowflake", JSImport.Default)
private object SnowflakeIcon extends js.Array[js.Any]

def Snowflake(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SnowflakeIcon, color, size)
