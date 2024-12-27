package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pencil-line", JSImport.Default)
private object PencilLineIcon extends js.Array[js.Any]

def PencilLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PencilLineIcon, color, size)
