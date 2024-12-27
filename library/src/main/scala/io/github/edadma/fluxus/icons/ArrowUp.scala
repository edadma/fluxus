package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up", JSImport.Default)
private object ArrowUpIcon extends js.Array[js.Any]

def ArrowUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpIcon, color, size)
