package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-to-line", JSImport.Default)
private object ArrowDownToLineIcon extends js.Array[js.Any]

def ArrowDownToLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownToLineIcon, color, size)
