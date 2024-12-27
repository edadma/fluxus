package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pipette", JSImport.Default)
private object PipetteIcon extends js.Array[js.Any]

def Pipette(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PipetteIcon, color, size)
