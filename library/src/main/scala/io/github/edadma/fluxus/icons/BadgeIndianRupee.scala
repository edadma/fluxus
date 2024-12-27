package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-indian-rupee", JSImport.Default)
private object BadgeIndianRupeeIcon extends js.Array[js.Any]

def BadgeIndianRupee(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeIndianRupeeIcon, color, size)
