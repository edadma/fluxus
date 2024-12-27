package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-justify-center", JSImport.Default)
private object AlignVerticalJustifyCenterIcon extends js.Array[js.Any]

def AlignVerticalJustifyCenter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalJustifyCenterIcon, color, size)
