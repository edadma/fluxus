package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cigarette-off", JSImport.Default)
private object CigaretteOffIcon extends js.Array[js.Any]

def CigaretteOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CigaretteOffIcon, color, size)
