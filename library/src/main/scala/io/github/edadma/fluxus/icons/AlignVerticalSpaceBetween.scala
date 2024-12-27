package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-space-between", JSImport.Default)
private object AlignVerticalSpaceBetweenIcon extends js.Array[js.Any]

def AlignVerticalSpaceBetween(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalSpaceBetweenIcon, color, size)
