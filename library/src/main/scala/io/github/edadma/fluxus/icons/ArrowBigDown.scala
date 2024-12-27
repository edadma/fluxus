package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-down", JSImport.Default)
private object ArrowBigDownIcon extends js.Array[js.Any]

def ArrowBigDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigDownIcon, color, size)
