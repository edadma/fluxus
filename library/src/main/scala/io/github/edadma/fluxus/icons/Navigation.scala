package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/navigation", JSImport.Default)
private object NavigationIcon extends js.Array[js.Any]

def Navigation(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NavigationIcon, color, size)
