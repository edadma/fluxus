package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-justify-start", JSImport.Default)
private object AlignVerticalJustifyStartIcon extends js.Array[js.Any]

def AlignVerticalJustifyStart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalJustifyStartIcon, color, size)
