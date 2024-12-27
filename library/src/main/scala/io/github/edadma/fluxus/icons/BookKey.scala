package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-key", JSImport.Default)
private object BookKeyIcon extends js.Array[js.Any]

def BookKey(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookKeyIcon, color, size)
