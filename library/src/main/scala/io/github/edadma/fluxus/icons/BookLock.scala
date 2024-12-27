package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-lock", JSImport.Default)
private object BookLockIcon extends js.Array[js.Any]

def BookLock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookLockIcon, color, size)
