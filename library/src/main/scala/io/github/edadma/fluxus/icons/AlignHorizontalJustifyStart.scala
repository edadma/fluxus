package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-justify-start", JSImport.Default)
private object AlignHorizontalJustifyStartIcon extends js.Array[js.Any]

def AlignHorizontalJustifyStart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalJustifyStartIcon, color, size)
