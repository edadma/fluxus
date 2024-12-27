package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/search-check", JSImport.Default)
private object SearchCheckIcon extends js.Array[js.Any]

def SearchCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SearchCheckIcon, color, size)
