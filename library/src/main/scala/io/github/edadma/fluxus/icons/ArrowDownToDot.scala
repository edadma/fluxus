package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-to-dot", JSImport.Default)
private object ArrowDownToDotIcon extends js.Array[js.Any]

def ArrowDownToDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownToDotIcon, color, size)
