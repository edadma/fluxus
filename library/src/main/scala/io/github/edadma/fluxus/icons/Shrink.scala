package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shrink", JSImport.Default)
private object ShrinkIcon extends js.Array[js.Any]

def Shrink(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShrinkIcon, color, size)
