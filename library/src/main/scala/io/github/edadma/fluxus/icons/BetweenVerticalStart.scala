package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/between-vertical-start", JSImport.Default)
private object BetweenVerticalStartIcon extends js.Array[js.Any]

def BetweenVerticalStart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BetweenVerticalStartIcon, color, size)
