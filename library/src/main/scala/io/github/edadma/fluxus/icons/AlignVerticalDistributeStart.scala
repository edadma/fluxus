package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-distribute-start", JSImport.Default)
private object AlignVerticalDistributeStartIcon extends js.Array[js.Any]

def AlignVerticalDistributeStart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalDistributeStartIcon, color, size)
