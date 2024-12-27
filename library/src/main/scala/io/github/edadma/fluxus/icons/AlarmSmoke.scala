package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/alarm-smoke", JSImport.Default)
private object AlarmSmokeIcon extends js.Array[js.Any]

def AlarmSmoke(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlarmSmokeIcon, color, size)
