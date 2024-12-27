package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bring-to-front", JSImport.Default)
private object BringToFrontIcon extends js.Array[js.Any]

def BringToFront(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BringToFrontIcon, color, size)
