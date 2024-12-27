package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-dot", JSImport.Default)
private object CircleDotIcon extends js.Array[js.Any]

def CircleDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleDotIcon, color, size)
