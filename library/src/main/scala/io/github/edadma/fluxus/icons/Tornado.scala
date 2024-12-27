package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tornado", JSImport.Default)
private object TornadoIcon extends js.Array[js.Any]

def Tornado(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TornadoIcon, color, size)
