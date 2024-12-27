package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-down", JSImport.Default)
private object BookDownIcon extends js.Array[js.Any]

def BookDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookDownIcon, color, size)
