package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/indent-decrease", JSImport.Default)
private object IndentDecreaseIcon extends js.Array[js.Any]

def IndentDecrease(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(IndentDecreaseIcon, color, size)
