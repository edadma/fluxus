package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/diameter", JSImport.Default)
private object DiameterIcon extends js.Array[js.Any]

def Diameter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DiameterIcon, color, size)
