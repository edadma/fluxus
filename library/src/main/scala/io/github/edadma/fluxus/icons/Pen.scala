package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pen", JSImport.Default)
private object PenIcon extends js.Array[js.Any]

def Pen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PenIcon, color, size)
