package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-dashed", JSImport.Default)
private object CircleDashedIcon extends js.Array[js.Any]

def CircleDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleDashedIcon, color, size)
