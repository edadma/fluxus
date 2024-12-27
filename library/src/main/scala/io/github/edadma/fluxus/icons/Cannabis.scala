package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cannabis", JSImport.Default)
private object CannabisIcon extends js.Array[js.Any]

def Cannabis(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CannabisIcon, color, size)
