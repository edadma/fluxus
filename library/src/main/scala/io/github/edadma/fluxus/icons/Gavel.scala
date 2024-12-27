package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gavel", JSImport.Default)
private object GavelIcon extends js.Array[js.Any]

def Gavel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GavelIcon, color, size)
