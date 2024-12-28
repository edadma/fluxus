package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-right", JSImport.Default)
private object ArrowDownRightIcon extends js.Array[js.Any]

def ArrowDownRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownRightIcon, color, size)