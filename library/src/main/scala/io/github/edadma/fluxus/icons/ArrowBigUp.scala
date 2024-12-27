package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-up", JSImport.Default)
private object ArrowBigUpIcon extends js.Array[js.Any]

def ArrowBigUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigUpIcon, color, size)
