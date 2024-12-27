package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-left", JSImport.Default)
private object ArrowDownLeftIcon extends js.Array[js.Any]

def ArrowDownLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownLeftIcon, color, size)
