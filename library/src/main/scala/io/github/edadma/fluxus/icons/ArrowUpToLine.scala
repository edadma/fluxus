package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-to-line", JSImport.Default)
private object ArrowUpToLineIcon extends js.Array[js.Any]

def ArrowUpToLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpToLineIcon, color, size)
