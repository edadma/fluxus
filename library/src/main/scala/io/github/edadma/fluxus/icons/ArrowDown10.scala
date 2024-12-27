package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-1-0", JSImport.Default)
private object ArrowDown10Icon extends js.Array[js.Any]

def ArrowDown10(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDown10Icon, color, size)
