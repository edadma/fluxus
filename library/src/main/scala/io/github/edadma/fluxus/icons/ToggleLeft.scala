package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/toggle-left", JSImport.Default)
private object ToggleLeftIcon extends js.Array[js.Any]

def ToggleLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ToggleLeftIcon, color, size)