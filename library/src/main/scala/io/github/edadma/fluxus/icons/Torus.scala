package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/torus", JSImport.Default)
private object TorusIcon extends js.Array[js.Any]

def Torus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TorusIcon, color, size)
