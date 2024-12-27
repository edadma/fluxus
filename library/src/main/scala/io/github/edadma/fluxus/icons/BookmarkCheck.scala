package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bookmark-check", JSImport.Default)
private object BookmarkCheckIcon extends js.Array[js.Any]

def BookmarkCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookmarkCheckIcon, color, size)
