package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-right-left", JSImport.Default)
private object ArrowRightLeftIcon extends js.Array[js.Any]

def ArrowRightLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowRightLeftIcon, color, size)
