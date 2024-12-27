package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/siren", JSImport.Default)
private object SirenIcon extends js.Array[js.Any]

def Siren(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SirenIcon, color, size)
