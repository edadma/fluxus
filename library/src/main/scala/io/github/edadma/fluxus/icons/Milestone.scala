package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/milestone", JSImport.Default)
private object MilestoneIcon extends js.Array[js.Any]

def Milestone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MilestoneIcon, color, size)
