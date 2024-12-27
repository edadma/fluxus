package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-right-from-line", JSImport.Default)
private object ArrowRightFromLineIcon extends js.Array[js.Any]

def ArrowRightFromLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowRightFromLineIcon, color, size)
