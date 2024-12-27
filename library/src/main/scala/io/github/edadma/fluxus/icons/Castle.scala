package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/castle", JSImport.Default)
private object CastleIcon extends js.Array[js.Any]

def Castle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CastleIcon, color, size)
