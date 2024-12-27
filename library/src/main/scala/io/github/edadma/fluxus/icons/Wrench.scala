package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wrench", JSImport.Default)
private object WrenchIcon extends js.Array[js.Any]

def Wrench(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WrenchIcon, color, size)
