package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/award", JSImport.Default)
private object AwardIcon extends js.Array[js.Any]

def Award(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AwardIcon, color, size)
