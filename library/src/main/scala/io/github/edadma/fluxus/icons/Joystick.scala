package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/joystick", JSImport.Default)
private object JoystickIcon extends js.Array[js.Any]

def Joystick(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(JoystickIcon, color, size)
