package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mouse-off", JSImport.Default)
private object MouseOffIcon extends js.Array[js.Any]

def MouseOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MouseOffIcon, color, size)
