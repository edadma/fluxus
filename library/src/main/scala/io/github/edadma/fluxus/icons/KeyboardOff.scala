package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/keyboard-off", JSImport.Default)
private object KeyboardOffIcon extends js.Array[js.Any]

def KeyboardOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(KeyboardOffIcon, color, size)
