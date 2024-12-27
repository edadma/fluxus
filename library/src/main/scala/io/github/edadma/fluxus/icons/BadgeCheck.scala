package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-check", JSImport.Default)
private object BadgeCheckIcon extends js.Array[js.Any]

def BadgeCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeCheckIcon, color, size)
