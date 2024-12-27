package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/goal", JSImport.Default)
private object GoalIcon extends js.Array[js.Any]

def Goal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GoalIcon, color, size)
