package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-left", JSImport.Default)
private object AlignLeftIcon extends js.Array[js.Any]

def AlignLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignLeftIcon, color, size)
