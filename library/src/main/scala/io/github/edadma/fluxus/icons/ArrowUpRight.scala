package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-right", JSImport.Default)
private object ArrowUpRightIcon extends js.Array[js.Any]

def ArrowUpRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpRightIcon, color, size)
