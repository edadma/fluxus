package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/test-tube-diagonal", JSImport.Default)
private object TestTubeDiagonalIcon extends js.Array[js.Any]

def TestTubeDiagonal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TestTubeDiagonalIcon, color, size)
