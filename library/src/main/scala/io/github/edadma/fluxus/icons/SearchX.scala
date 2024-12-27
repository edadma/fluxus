package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/search-x", JSImport.Default)
private object SearchXIcon extends js.Array[js.Any]

def SearchX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SearchXIcon, color, size)
