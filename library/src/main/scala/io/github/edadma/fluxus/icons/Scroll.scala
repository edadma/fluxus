package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scroll", JSImport.Default)
private object ScrollIcon extends js.Array[js.Any]

def Scroll(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScrollIcon, color, size)
