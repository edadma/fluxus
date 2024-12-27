package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-justify-end", JSImport.Default)
private object AlignVerticalJustifyEndIcon extends js.Array[js.Any]

def AlignVerticalJustifyEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalJustifyEndIcon, color, size)
