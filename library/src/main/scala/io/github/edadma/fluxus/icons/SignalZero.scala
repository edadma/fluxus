package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signal-zero", JSImport.Default)
private object SignalZeroIcon extends js.Array[js.Any]

def SignalZero(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignalZeroIcon, color, size)
