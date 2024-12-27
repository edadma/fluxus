package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/meh", JSImport.Default)
private object MehIcon extends js.Array[js.Any]

def Meh(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MehIcon, color, size)
