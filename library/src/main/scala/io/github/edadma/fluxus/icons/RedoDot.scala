package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/redo-dot", JSImport.Default)
private object RedoDotIcon extends js.Array[js.Any]

def RedoDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RedoDotIcon, color, size)
