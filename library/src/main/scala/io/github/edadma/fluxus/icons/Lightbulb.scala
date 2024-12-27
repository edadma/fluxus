package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lightbulb", JSImport.Default)
private object LightbulbIcon extends js.Array[js.Any]

def Lightbulb(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LightbulbIcon, color, size)
