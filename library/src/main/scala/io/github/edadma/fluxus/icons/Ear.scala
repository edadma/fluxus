package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ear", JSImport.Default)
private object EarIcon extends js.Array[js.Any]

def Ear(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EarIcon, color, size)
