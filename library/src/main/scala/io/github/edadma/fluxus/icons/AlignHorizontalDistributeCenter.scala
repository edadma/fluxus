package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-horizontal-distribute-center", JSImport.Default)
private object AlignHorizontalDistributeCenterIcon extends js.Array[js.Any]

def AlignHorizontalDistributeCenter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignHorizontalDistributeCenterIcon, color, size)
