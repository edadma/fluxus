package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-info", JSImport.Default)
private object BadgeInfoIcon extends js.Array[js.Any]

def BadgeInfo(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeInfoIcon, color, size)
