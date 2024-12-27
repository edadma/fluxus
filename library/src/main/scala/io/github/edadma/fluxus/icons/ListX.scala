package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-x", JSImport.Default)
private object ListXIcon extends js.Array[js.Any]

def ListX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListXIcon, color, size)
