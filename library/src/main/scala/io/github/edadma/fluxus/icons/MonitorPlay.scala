package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-play", JSImport.Default)
private object MonitorPlayIcon extends js.Array[js.Any]

def MonitorPlay(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorPlayIcon, color, size)
