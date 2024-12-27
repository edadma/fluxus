package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/timer-off", JSImport.Default)
private object TimerOffIcon extends js.Array[js.Any]

def TimerOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TimerOffIcon, color, size)
