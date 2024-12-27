package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-end-vertical", JSImport.Default)
private object AlignEndVerticalIcon extends js.Array[js.Any]

def AlignEndVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignEndVerticalIcon, color, size)
