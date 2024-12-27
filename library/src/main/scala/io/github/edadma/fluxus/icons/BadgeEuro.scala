package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-euro", JSImport.Default)
private object BadgeEuroIcon extends js.Array[js.Any]

def BadgeEuro(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeEuroIcon, color, size)
