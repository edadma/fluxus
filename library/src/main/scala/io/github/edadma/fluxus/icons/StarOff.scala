package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/star-off", JSImport.Default)
private object StarOffIcon extends js.Array[js.Any]

def StarOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StarOffIcon, color, size)
