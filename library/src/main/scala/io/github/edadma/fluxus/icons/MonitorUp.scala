package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-up", JSImport.Default)
private object MonitorUpIcon extends js.Array[js.Any]

def MonitorUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorUpIcon, color, size)
