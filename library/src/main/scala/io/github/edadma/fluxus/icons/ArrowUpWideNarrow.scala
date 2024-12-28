package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-wide-narrow", JSImport.Default)
private object ArrowUpWideNarrowIcon extends js.Array[js.Any]

def ArrowUpWideNarrow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpWideNarrowIcon, color, size)