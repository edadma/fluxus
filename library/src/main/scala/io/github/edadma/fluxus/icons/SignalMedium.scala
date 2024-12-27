package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signal-medium", JSImport.Default)
private object SignalMediumIcon extends js.Array[js.Any]

def SignalMedium(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignalMediumIcon, color, size)
