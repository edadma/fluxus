package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/boxes", JSImport.Default)
private object BoxesIcon extends js.Array[js.Any]

def Boxes(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BoxesIcon, color, size)
