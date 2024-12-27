package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/palette", JSImport.Default)
private object PaletteIcon extends js.Array[js.Any]

def Palette(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PaletteIcon, color, size)
