package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrows-up-from-line", JSImport.Default)
private object ArrowsUpFromLineIcon extends js.Array[js.Any]

def ArrowsUpFromLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowsUpFromLineIcon, color, size)
