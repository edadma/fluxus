package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-left", JSImport.Default)
private object ArrowUpLeftIcon extends js.Array[js.Any]

def ArrowUpLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpLeftIcon, color, size)
