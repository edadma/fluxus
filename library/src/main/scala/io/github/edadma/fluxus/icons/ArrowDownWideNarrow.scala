package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-wide-narrow", JSImport.Default)
private object ArrowDownWideNarrowIcon extends js.Array[js.Any]

def ArrowDownWideNarrow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownWideNarrowIcon, color, size)
