package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rectangle-horizontal", JSImport.Default)
private object RectangleHorizontalIcon extends js.Array[js.Any]

def RectangleHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RectangleHorizontalIcon, color, size)
