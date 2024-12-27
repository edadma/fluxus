package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/log-in", JSImport.Default)
private object LogInIcon extends js.Array[js.Any]

def LogIn(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LogInIcon, color, size)
