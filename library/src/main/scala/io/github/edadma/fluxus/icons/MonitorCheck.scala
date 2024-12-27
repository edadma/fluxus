package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-check", JSImport.Default)
private object MonitorCheckIcon extends js.Array[js.Any]

def MonitorCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorCheckIcon, color, size)
