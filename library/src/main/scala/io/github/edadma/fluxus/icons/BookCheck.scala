package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-check", JSImport.Default)
private object BookCheckIcon extends js.Array[js.Any]

def BookCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookCheckIcon, color, size)
