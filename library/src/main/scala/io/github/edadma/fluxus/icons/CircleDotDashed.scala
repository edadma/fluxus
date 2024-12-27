package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-dot-dashed", JSImport.Default)
private object CircleDotDashedIcon extends js.Array[js.Any]

def CircleDotDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleDotDashedIcon, color, size)
