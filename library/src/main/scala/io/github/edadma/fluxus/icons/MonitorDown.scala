package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-down", JSImport.Default)
private object MonitorDownIcon extends js.Array[js.Any]

def MonitorDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorDownIcon, color, size)
