package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-open-text", JSImport.Default)
private object BookOpenTextIcon extends js.Array[js.Any]

def BookOpenText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookOpenTextIcon, color, size)
