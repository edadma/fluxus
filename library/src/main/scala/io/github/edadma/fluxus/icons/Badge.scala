package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge", JSImport.Default)
private object BadgeIcon extends js.Array[js.Any]

def Badge(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeIcon, color, size)
