package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signal-high", JSImport.Default)
private object SignalHighIcon extends js.Array[js.Any]

def SignalHigh(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignalHighIcon, color, size)
