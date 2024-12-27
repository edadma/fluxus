package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevron-right", JSImport.Default)
private object ChevronRightIcon extends js.Array[js.Any]

def ChevronRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronRightIcon, color, size)
