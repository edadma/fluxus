package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-justify-center", JSImport.Default)
private object AlignHorizontalJustifyCenterIcon extends js.Array[js.Any]

def AlignHorizontalJustifyCenter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalJustifyCenterIcon, color, size)
