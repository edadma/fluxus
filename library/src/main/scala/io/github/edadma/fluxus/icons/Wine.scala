package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wine", JSImport.Default)
private object WineIcon extends js.Array[js.Any]

def Wine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WineIcon, color, size)
