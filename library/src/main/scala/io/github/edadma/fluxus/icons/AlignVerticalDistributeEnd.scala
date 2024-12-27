package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-distribute-end", JSImport.Default)
private object AlignVerticalDistributeEndIcon extends js.Array[js.Any]

def AlignVerticalDistributeEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalDistributeEndIcon, color, size)
