package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/grip", JSImport.Default)
private object GripIcon extends js.Array[js.Any]

def Grip(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GripIcon, color, size)
