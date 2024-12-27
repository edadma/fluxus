package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevron-first", JSImport.Default)
private object ChevronFirstIcon extends js.Array[js.Any]

def ChevronFirst(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronFirstIcon, color, size)
