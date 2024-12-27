package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/haze", JSImport.Default)
private object HazeIcon extends js.Array[js.Any]

def Haze(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HazeIcon, color, size)
