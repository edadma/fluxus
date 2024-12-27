package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/keyboard", JSImport.Default)
private object KeyboardIcon extends js.Array[js.Any]

def Keyboard(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(KeyboardIcon, color, size)
