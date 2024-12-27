package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ear-off", JSImport.Default)
private object EarOffIcon extends js.Array[js.Any]

def EarOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EarOffIcon, color, size)
