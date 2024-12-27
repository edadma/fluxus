package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-left-from-line", JSImport.Default)
private object ArrowLeftFromLineIcon extends js.Array[js.Any]

def ArrowLeftFromLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowLeftFromLineIcon, color, size)
