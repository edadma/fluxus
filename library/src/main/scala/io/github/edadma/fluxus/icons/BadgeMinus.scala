package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-minus", JSImport.Default)
private object BadgeMinusIcon extends js.Array[js.Any]

def BadgeMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeMinusIcon, color, size)
