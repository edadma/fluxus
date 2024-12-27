package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bookmark-plus", JSImport.Default)
private object BookmarkPlusIcon extends js.Array[js.Any]

def BookmarkPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookmarkPlusIcon, color, size)
