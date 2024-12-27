package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-off", JSImport.Default)
private object CircleOffIcon extends js.Array[js.Any]

def CircleOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleOffIcon, color, size)
