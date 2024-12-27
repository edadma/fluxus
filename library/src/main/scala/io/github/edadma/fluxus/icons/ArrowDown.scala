package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down", JSImport.Default)
private object ArrowDownIcon extends js.Array[js.Any]

def ArrowDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownIcon, color, size)
