package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-from-line", JSImport.Default)
private object ArrowUpFromLineIcon extends js.Array[js.Any]

def ArrowUpFromLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpFromLineIcon, color, size)
