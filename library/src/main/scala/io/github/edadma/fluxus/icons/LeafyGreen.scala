package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/leafy-green", JSImport.Default)
private object LeafyGreenIcon extends js.Array[js.Any]

def LeafyGreen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LeafyGreenIcon, color, size)
