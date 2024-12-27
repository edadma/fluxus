package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-x", JSImport.Default)
private object MonitorXIcon extends js.Array[js.Any]

def MonitorX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorXIcon, color, size)
