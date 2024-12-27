package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/drill", JSImport.Default)
private object DrillIcon extends js.Array[js.Any]

def Drill(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DrillIcon, color, size)
