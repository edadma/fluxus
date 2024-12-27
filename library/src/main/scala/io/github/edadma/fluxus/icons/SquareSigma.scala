package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-sigma", JSImport.Default)
private object SquareSigmaIcon extends js.Array[js.Any]

def SquareSigma(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareSigmaIcon, color, size)
