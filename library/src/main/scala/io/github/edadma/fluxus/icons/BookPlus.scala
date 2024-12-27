package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-plus", JSImport.Default)
private object BookPlusIcon extends js.Array[js.Any]

def BookPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookPlusIcon, color, size)
