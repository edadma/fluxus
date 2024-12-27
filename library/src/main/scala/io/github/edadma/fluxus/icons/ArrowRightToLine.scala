package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-right-to-line", JSImport.Default)
private object ArrowRightToLineIcon extends js.Array[js.Any]

def ArrowRightToLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowRightToLineIcon, color, size)
