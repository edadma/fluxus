package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-distribute-center", JSImport.Default)
private object AlignVerticalDistributeCenterIcon extends js.Array[js.Any]

def AlignVerticalDistributeCenter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalDistributeCenterIcon, color, size)
