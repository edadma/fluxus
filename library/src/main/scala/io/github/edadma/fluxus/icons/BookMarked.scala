package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-marked", JSImport.Default)
private object BookMarkedIcon extends js.Array[js.Any]

def BookMarked(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookMarkedIcon, color, size)
