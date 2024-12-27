package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-narrow-wide", JSImport.Default)
private object ArrowUpNarrowWideIcon extends js.Array[js.Any]

def ArrowUpNarrowWide(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUpNarrowWideIcon, color, size)
