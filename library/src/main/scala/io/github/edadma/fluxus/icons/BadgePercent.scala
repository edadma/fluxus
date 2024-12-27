package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-percent", JSImport.Default)
private object BadgePercentIcon extends js.Array[js.Any]

def BadgePercent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgePercentIcon, color, size)
