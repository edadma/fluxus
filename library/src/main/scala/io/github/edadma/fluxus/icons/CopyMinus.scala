package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/copy-minus", JSImport.Default)
private object CopyMinusIcon extends js.Array[js.Any]

def CopyMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CopyMinusIcon, color, size)
