package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/toggle-right", JSImport.Default)
private object ToggleRightIcon extends js.Array[js.Any]

def ToggleRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ToggleRightIcon, color, size)
