package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bold", JSImport.Default)
private object BoldIcon extends js.Array[js.Any]

def Bold(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BoldIcon, color, size)
