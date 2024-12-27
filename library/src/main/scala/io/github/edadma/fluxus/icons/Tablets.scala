package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tablets", JSImport.Default)
private object TabletsIcon extends js.Array[js.Any]

def Tablets(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TabletsIcon, color, size)
