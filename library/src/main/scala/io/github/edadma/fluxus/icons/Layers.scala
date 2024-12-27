package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/layers", JSImport.Default)
private object LayersIcon extends js.Array[js.Any]

def Layers(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LayersIcon, color, size)
