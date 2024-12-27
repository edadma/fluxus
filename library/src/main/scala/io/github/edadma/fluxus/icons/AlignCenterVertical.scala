package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-center-vertical", JSImport.Default)
private object AlignCenterVerticalIcon extends js.Array[js.Any]

def AlignCenterVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignCenterVerticalIcon, color, size)
