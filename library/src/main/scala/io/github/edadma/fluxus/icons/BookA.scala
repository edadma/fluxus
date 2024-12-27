package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-a", JSImport.Default)
private object BookAIcon extends js.Array[js.Any]

def BookA(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookAIcon, color, size)
