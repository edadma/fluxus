package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-copy", JSImport.Default)
private object BookCopyIcon extends js.Array[js.Any]

def BookCopy(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookCopyIcon, color, size)
