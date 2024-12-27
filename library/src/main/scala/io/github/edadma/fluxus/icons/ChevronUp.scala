package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevron-up", JSImport.Default)
private object ChevronUpIcon extends js.Array[js.Any]

def ChevronUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronUpIcon, color, size)
