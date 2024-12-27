package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevron-left", JSImport.Default)
private object ChevronLeftIcon extends js.Array[js.Any]

def ChevronLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronLeftIcon, color, size)
