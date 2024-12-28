package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/search-slash", JSImport.Default)
private object SearchSlashIcon extends js.Array[js.Any]

def SearchSlash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SearchSlashIcon, color, size)