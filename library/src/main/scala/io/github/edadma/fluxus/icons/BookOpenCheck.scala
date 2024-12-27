package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-open-check", JSImport.Default)
private object BookOpenCheckIcon extends js.Array[js.Any]

def BookOpenCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookOpenCheckIcon, color, size)
