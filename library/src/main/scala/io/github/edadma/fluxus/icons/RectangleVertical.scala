package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rectangle-vertical", JSImport.Default)
private object RectangleVerticalIcon extends js.Array[js.Any]

def RectangleVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RectangleVerticalIcon, color, size)
