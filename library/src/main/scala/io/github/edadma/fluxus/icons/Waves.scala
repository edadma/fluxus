package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/waves", JSImport.Default)
private object WavesIcon extends js.Array[js.Any]

def Waves(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WavesIcon, color, size)
