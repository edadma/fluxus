package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/slice", JSImport.Default)
private object SliceIcon extends js.Array[js.Any]

def Slice(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SliceIcon, color, size)
