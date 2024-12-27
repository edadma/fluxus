package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cone", JSImport.Default)
private object ConeIcon extends js.Array[js.Any]

def Cone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ConeIcon, color, size)
