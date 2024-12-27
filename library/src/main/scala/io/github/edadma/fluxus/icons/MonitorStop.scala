package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-stop", JSImport.Default)
private object MonitorStopIcon extends js.Array[js.Any]

def MonitorStop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorStopIcon, color, size)
