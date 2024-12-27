package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-x", JSImport.Default)
private object BookXIcon extends js.Array[js.Any]

def BookX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookXIcon, color, size)
