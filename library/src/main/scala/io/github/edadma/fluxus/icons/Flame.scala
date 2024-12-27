package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flame", JSImport.Default)
private object FlameIcon extends js.Array[js.Any]

def Flame(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlameIcon, color, size)
