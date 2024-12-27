package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lamp", JSImport.Default)
private object LampIcon extends js.Array[js.Any]

def Lamp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LampIcon, color, size)
