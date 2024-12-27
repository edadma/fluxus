package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/minimize", JSImport.Default)
private object MinimizeIcon extends js.Array[js.Any]

def Minimize(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MinimizeIcon, color, size)
