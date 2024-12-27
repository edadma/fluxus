package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-left", JSImport.Default)
private object SquareArrowLeftIcon extends js.Array[js.Any]

def SquareArrowLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowLeftIcon, color, size)
