package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/memory-stick", JSImport.Default)
private object MemoryStickIcon extends js.Array[js.Any]

def MemoryStick(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MemoryStickIcon, color, size)
