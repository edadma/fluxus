package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-x", JSImport.Default)
private object BadgeXIcon extends js.Array[js.Any]

def BadgeX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeXIcon, color, size)
