package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bookmark-minus", JSImport.Default)
private object BookmarkMinusIcon extends js.Array[js.Any]

def BookmarkMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookmarkMinusIcon, color, size)
