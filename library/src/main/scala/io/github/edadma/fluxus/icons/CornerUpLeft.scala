package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-up-left", JSImport.Default)
private object CornerUpLeftIcon extends js.Array[js.Any]

def CornerUpLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerUpLeftIcon, color, size)
