package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/briefcase-conveyor-belt", JSImport.Default)
private object BriefcaseConveyorBeltIcon extends js.Array[js.Any]

def BriefcaseConveyorBelt(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BriefcaseConveyorBeltIcon, color, size)
