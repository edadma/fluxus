package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gamepad-2", JSImport.Default)
private object Gamepad2Icon extends js.Array[js.Any]

def Gamepad2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Gamepad2Icon, color, size)
