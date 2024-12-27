package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/plug", JSImport.Default)
private object PlugIcon extends js.Array[js.Any]

def Plug(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PlugIcon, color, size)
