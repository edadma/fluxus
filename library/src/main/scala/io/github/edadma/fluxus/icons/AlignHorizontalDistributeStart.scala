package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-distribute-start", JSImport.Default)
private object AlignHorizontalDistributeStartIcon extends js.Array[js.Any]

def AlignHorizontalDistributeStart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalDistributeStartIcon, color, size)
