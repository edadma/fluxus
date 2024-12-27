package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/footprints", JSImport.Default)
private object FootprintsIcon extends js.Array[js.Any]

def Footprints(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FootprintsIcon, color, size)
