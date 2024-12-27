package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wheat-off", JSImport.Default)
private object WheatOffIcon extends js.Array[js.Any]

def WheatOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WheatOffIcon, color, size)
