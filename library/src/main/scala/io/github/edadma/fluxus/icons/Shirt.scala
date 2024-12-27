package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shirt", JSImport.Default)
private object ShirtIcon extends js.Array[js.Any]

def Shirt(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShirtIcon, color, size)
