package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/a-arrow-down", JSImport.Default)
private object AArrowDownIcon extends js.Array[js.Any]

def AArrowDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AArrowDownIcon, color, size)
