package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-end", JSImport.Default)
private object ListEndIcon extends js.Array[js.Any]

def ListEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListEndIcon, color, size)
