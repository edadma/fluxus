package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-from-dot", JSImport.Default)
private object ArrowUpFromDotIcon extends js.Array[js.Any]

def ArrowUpFromDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpFromDotIcon, color, size)
