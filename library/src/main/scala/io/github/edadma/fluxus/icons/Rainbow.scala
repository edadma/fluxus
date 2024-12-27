package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rainbow", JSImport.Default)
private object RainbowIcon extends js.Array[js.Any]

def Rainbow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RainbowIcon, color, size)
