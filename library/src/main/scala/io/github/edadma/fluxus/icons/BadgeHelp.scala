package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-help", JSImport.Default)
private object BadgeHelpIcon extends js.Array[js.Any]

def BadgeHelp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeHelpIcon, color, size)
