package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-radical", JSImport.Default)
private object SquareRadicalIcon extends js.Array[js.Any]

def SquareRadical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareRadicalIcon, color, size)
