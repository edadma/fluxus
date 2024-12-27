package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-justify-end", JSImport.Default)
private object AlignHorizontalJustifyEndIcon extends js.Array[js.Any]

def AlignHorizontalJustifyEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalJustifyEndIcon, color, size)
