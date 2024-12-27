package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/server-off", JSImport.Default)
private object ServerOffIcon extends js.Array[js.Any]

def ServerOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ServerOffIcon, color, size)
