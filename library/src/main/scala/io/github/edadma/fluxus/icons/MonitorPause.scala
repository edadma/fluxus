package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-pause", JSImport.Default)
private object MonitorPauseIcon extends js.Array[js.Any]

def MonitorPause(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorPauseIcon, color, size)
