package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/recycle", JSImport.Default)
private object RecycleIcon extends js.Array[js.Any]

def Recycle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RecycleIcon, color, size)
