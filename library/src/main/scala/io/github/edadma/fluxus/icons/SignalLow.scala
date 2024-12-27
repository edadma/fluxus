package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signal-low", JSImport.Default)
private object SignalLowIcon extends js.Array[js.Any]

def SignalLow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignalLowIcon, color, size)
