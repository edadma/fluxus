package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/timer", JSImport.Default)
private object TimerIcon extends js.Array[js.Any]

def Timer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TimerIcon, color, size)
