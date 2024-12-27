package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bookmark", JSImport.Default)
private object BookmarkIcon extends js.Array[js.Any]

def Bookmark(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookmarkIcon, color, size)
