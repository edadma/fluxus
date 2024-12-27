package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/between-horizontal-start", JSImport.Default)
private object BetweenHorizontalStartIcon extends js.Array[js.Any]

def BetweenHorizontalStart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BetweenHorizontalStartIcon, color, size)
