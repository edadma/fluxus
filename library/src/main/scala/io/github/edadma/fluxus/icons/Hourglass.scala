package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hourglass", JSImport.Default)
private object HourglassIcon extends js.Array[js.Any]

def Hourglass(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HourglassIcon, color, size)
