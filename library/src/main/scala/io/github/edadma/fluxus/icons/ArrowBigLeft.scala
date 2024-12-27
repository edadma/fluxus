package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-left", JSImport.Default)
private object ArrowBigLeftIcon extends js.Array[js.Any]

def ArrowBigLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigLeftIcon, color, size)
