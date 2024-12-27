package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-up", JSImport.Default)
private object BookUpIcon extends js.Array[js.Any]

def BookUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookUpIcon, color, size)
