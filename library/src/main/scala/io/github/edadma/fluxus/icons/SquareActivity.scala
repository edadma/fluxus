package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-activity", JSImport.Default)
private object SquareActivityIcon extends js.Array[js.Any]

def SquareActivity(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareActivityIcon, color, size)
