package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-from-line", JSImport.Default)
private object ArrowDownFromLineIcon extends js.Array[js.Any]

def ArrowDownFromLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownFromLineIcon, color, size)
