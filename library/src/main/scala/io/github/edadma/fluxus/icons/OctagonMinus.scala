package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/octagon-minus", JSImport.Default)
private object OctagonMinusIcon extends js.Array[js.Any]

def OctagonMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OctagonMinusIcon, color, size)
