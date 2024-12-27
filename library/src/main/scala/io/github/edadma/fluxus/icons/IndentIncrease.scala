package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/indent-increase", JSImport.Default)
private object IndentIncreaseIcon extends js.Array[js.Any]

def IndentIncrease(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(IndentIncreaseIcon, color, size)
