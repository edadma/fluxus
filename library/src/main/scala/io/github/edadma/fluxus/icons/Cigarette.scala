package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cigarette", JSImport.Default)
private object CigaretteIcon extends js.Array[js.Any]

def Cigarette(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CigaretteIcon, color, size)
