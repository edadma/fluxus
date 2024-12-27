package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-a-z", JSImport.Default)
private object ArrowUpAZIcon extends js.Array[js.Any]

def ArrowUpAZ(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpAZIcon, color, size)
