package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map", JSImport.Default)
private object MapIcon extends js.Array[js.Any]

def Map(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapIcon, color, size)
