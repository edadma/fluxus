package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/repeat", JSImport.Default)
private object RepeatIcon extends js.Array[js.Any]

def Repeat(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RepeatIcon, color, size)
