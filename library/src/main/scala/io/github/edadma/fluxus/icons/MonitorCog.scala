package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-cog", JSImport.Default)
private object MonitorCogIcon extends js.Array[js.Any]

def MonitorCog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorCogIcon, color, size)
