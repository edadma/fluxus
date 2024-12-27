package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/between-horizontal-end", JSImport.Default)
private object BetweenHorizontalEndIcon extends js.Array[js.Any]

def BetweenHorizontalEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BetweenHorizontalEndIcon, color, size)
