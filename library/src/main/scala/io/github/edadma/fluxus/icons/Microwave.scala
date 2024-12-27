package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/microwave", JSImport.Default)
private object MicrowaveIcon extends js.Array[js.Any]

def Microwave(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MicrowaveIcon, color, size)
