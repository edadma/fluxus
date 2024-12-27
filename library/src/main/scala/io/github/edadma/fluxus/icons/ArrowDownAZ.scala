package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-a-z", JSImport.Default)
private object ArrowDownAZIcon extends js.Array[js.Any]

def ArrowDownAZ(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownAZIcon, color, size)
