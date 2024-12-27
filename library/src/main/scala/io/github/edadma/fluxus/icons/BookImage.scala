package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-image", JSImport.Default)
private object BookImageIcon extends js.Array[js.Any]

def BookImage(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookImageIcon, color, size)
