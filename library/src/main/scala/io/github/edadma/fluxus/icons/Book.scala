package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book", JSImport.Default)
private object BookIcon extends js.Array[js.Any]

def Book(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookIcon, color, size)
