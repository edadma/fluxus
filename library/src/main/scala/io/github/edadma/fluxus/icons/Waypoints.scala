package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/waypoints", JSImport.Default)
private object WaypointsIcon extends js.Array[js.Any]

def Waypoints(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WaypointsIcon, color, size)
