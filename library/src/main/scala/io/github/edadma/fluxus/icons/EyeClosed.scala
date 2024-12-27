package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/eye-closed", JSImport.Default)
private object EyeClosedIcon extends js.Array[js.Any]

def EyeClosed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EyeClosedIcon, color, size)
