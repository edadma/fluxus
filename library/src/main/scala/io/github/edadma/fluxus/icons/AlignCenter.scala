package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-center", JSImport.Default)
private object AlignCenterIcon extends js.Array[js.Any]

def AlignCenter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignCenterIcon, color, size)
