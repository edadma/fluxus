package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lightbulb-off", JSImport.Default)
private object LightbulbOffIcon extends js.Array[js.Any]

def LightbulbOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LightbulbOffIcon, color, size)
