package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cup-soda", JSImport.Default)
private object CupSodaIcon extends js.Array[js.Any]

def CupSoda(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CupSodaIcon, color, size)
