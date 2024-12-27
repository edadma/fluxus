package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-speaker", JSImport.Default)
private object MonitorSpeakerIcon extends js.Array[js.Any]

def MonitorSpeaker(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorSpeakerIcon, color, size)
