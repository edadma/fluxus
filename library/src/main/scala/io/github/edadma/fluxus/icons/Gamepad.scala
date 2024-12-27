package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gamepad", JSImport.Default)
private object GamepadIcon extends js.Array[js.Any]

def Gamepad(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GamepadIcon, color, size)
