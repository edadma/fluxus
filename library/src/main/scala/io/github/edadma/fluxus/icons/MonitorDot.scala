package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-dot", JSImport.Default)
private object MonitorDotIcon extends js.Array[js.Any]

def MonitorDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorDotIcon, color, size)
