package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/redo", JSImport.Default)
private object RedoIcon extends js.Array[js.Any]

def Redo(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RedoIcon, color, size)
