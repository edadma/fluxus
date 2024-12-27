package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-sliders", JSImport.Default)
private object FileSlidersIcon extends js.Array[js.Any]

def FileSliders(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileSlidersIcon, color, size)
