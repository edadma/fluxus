package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heart-off", JSImport.Default)
private object HeartOffIcon extends js.Array[js.Any]

def HeartOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeartOffIcon, color, size)
