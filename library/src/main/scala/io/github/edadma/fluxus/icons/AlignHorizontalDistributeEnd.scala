package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-distribute-end", JSImport.Default)
private object AlignHorizontalDistributeEndIcon extends js.Array[js.Any]

def AlignHorizontalDistributeEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalDistributeEndIcon, color, size)
