package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/construction", JSImport.Default)
private object ConstructionIcon extends js.Array[js.Any]

def Construction(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ConstructionIcon, color, size)
