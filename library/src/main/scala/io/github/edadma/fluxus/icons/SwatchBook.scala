package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/swatch-book", JSImport.Default)
private object SwatchBookIcon extends js.Array[js.Any]

def SwatchBook(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SwatchBookIcon, color, size)
