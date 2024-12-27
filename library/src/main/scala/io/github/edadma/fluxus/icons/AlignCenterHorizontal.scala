package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-center-horizontal", JSImport.Default)
private object AlignCenterHorizontalIcon extends js.Array[js.Any]

def AlignCenterHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignCenterHorizontalIcon, color, size)
