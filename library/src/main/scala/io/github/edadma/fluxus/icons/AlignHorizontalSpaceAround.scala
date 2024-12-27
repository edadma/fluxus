package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-space-around", JSImport.Default)
private object AlignHorizontalSpaceAroundIcon extends js.Array[js.Any]

def AlignHorizontalSpaceAround(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalSpaceAroundIcon, color, size)
