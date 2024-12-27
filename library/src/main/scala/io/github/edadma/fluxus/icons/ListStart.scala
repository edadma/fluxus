package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-start", JSImport.Default)
private object ListStartIcon extends js.Array[js.Any]

def ListStart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListStartIcon, color, size)
