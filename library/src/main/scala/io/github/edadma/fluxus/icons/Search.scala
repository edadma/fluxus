package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/search", JSImport.Default)
private object SearchIcon extends js.Array[js.Any]

def Search(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SearchIcon, color, size)
