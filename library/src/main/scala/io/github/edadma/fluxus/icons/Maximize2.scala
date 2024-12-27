package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/maximize-2", JSImport.Default)
private object Maximize2Icon extends js.Array[js.Any]

def Maximize2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Maximize2Icon, color, size)
