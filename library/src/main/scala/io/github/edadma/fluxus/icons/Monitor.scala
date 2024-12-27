package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor", JSImport.Default)
private object MonitorIcon extends js.Array[js.Any]

def Monitor(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorIcon, color, size)
