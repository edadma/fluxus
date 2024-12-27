package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/glass-water", JSImport.Default)
private object GlassWaterIcon extends js.Array[js.Any]

def GlassWater(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GlassWaterIcon, color, size)
