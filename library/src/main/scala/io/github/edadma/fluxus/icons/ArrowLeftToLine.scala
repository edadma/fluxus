package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-left-to-line", JSImport.Default)
private object ArrowLeftToLineIcon extends js.Array[js.Any]

def ArrowLeftToLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowLeftToLineIcon, color, size)
