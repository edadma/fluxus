package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pause", JSImport.Default)
private object PauseIcon extends js.Array[js.Any]

def Pause(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PauseIcon, color, size)