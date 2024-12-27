package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-right", JSImport.Default)
private object AlignRightIcon extends js.Array[js.Any]

def AlignRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignRightIcon, color, size)
