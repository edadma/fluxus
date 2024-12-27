package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pill-bottle", JSImport.Default)
private object PillBottleIcon extends js.Array[js.Any]

def PillBottle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PillBottleIcon, color, size)
