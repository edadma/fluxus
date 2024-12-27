package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-type", JSImport.Default)
private object BookTypeIcon extends js.Array[js.Any]

def BookType(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookTypeIcon, color, size)
