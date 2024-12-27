package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/salad", JSImport.Default)
private object SaladIcon extends js.Array[js.Any]

def Salad(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SaladIcon, color, size)
