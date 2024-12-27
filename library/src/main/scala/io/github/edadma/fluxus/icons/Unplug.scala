package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/unplug", JSImport.Default)
private object UnplugIcon extends js.Array[js.Any]

def Unplug(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UnplugIcon, color, size)
