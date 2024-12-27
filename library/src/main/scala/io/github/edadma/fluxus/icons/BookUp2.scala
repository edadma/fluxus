package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-up-2", JSImport.Default)
private object BookUp2Icon extends js.Array[js.Any]

def BookUp2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookUp2Icon, color, size)
