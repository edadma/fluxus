package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pen-line", JSImport.Default)
private object PenLineIcon extends js.Array[js.Any]

def PenLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PenLineIcon, color, size)
