package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/octagon", JSImport.Default)
private object OctagonIcon extends js.Array[js.Any]

def Octagon(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OctagonIcon, color, size)
