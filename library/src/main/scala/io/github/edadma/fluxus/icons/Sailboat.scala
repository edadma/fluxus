package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sailboat", JSImport.Default)
private object SailboatIcon extends js.Array[js.Any]

def Sailboat(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SailboatIcon, color, size)
