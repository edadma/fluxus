package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-fading-arrow-up", JSImport.Default)
private object CircleFadingArrowUpIcon extends js.Array[js.Any]

def CircleFadingArrowUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleFadingArrowUpIcon, color, size)
