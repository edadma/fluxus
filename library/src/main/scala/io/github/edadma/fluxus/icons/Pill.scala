package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pill", JSImport.Default)
private object PillIcon extends js.Array[js.Any]

def Pill(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PillIcon, color, size)
