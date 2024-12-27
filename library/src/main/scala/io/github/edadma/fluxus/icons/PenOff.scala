package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pen-off", JSImport.Default)
private object PenOffIcon extends js.Array[js.Any]

def PenOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PenOffIcon, color, size)
