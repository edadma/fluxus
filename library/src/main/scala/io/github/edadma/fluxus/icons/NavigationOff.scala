package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/navigation-off", JSImport.Default)
private object NavigationOffIcon extends js.Array[js.Any]

def NavigationOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NavigationOffIcon, color, size)
