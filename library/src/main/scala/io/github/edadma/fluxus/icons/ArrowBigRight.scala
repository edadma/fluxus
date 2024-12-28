package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-right", JSImport.Default)
private object ArrowBigRightIcon extends js.Array[js.Any]

def ArrowBigRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigRightIcon, color, size)