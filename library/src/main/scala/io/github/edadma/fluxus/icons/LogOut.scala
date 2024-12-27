package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/log-out", JSImport.Default)
private object LogOutIcon extends js.Array[js.Any]

def LogOut(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LogOutIcon, color, size)
