package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-space-between", JSImport.Default)
private object AlignHorizontalSpaceBetweenIcon extends js.Array[js.Any]

def AlignHorizontalSpaceBetween(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalSpaceBetweenIcon, color, size)
