package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signal", JSImport.Default)
private object SignalIcon extends js.Array[js.Any]

def Signal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignalIcon, color, size)
