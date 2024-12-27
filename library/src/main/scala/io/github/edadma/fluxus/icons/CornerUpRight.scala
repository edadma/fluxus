package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-up-right", JSImport.Default)
private object CornerUpRightIcon extends js.Array[js.Any]

def CornerUpRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerUpRightIcon, color, size)
