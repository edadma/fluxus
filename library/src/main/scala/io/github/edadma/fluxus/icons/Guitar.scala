package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/guitar", JSImport.Default)
private object GuitarIcon extends js.Array[js.Any]

def Guitar(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GuitarIcon, color, size)
