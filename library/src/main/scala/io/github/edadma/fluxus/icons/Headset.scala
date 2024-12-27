package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/headset", JSImport.Default)
private object HeadsetIcon extends js.Array[js.Any]

def Headset(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeadsetIcon, color, size)
