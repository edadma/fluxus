package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-off", JSImport.Default)
private object MonitorOffIcon extends js.Array[js.Any]

def MonitorOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorOffIcon, color, size)
