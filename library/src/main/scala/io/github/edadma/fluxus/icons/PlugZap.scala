package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/plug-zap", JSImport.Default)
private object PlugZapIcon extends js.Array[js.Any]

def PlugZap(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PlugZapIcon, color, size)
