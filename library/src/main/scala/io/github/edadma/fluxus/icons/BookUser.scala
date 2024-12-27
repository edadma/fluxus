package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-user", JSImport.Default)
private object BookUserIcon extends js.Array[js.Any]

def BookUser(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookUserIcon, color, size)
