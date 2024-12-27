package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-heart", JSImport.Default)
private object BookHeartIcon extends js.Array[js.Any]

def BookHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookHeartIcon, color, size)
