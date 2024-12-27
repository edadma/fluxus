package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-russian-ruble", JSImport.Default)
private object BadgeRussianRubleIcon extends js.Array[js.Any]

def BadgeRussianRuble(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeRussianRubleIcon, color, size)
