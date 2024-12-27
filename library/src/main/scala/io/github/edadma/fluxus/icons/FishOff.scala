package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fish-off", JSImport.Default)
private object FishOffIcon extends js.Array[js.Any]

def FishOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FishOffIcon, color, size)
