package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lollipop", JSImport.Default)
private object LollipopIcon extends js.Array[js.Any]

def Lollipop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LollipopIcon, color, size)
