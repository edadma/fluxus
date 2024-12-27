package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-dashed", JSImport.Default)
private object BookDashedIcon extends js.Array[js.Any]

def BookDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookDashedIcon, color, size)
