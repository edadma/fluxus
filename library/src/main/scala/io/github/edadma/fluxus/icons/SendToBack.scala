package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/send-to-back", JSImport.Default)
private object SendToBackIcon extends js.Array[js.Any]

def SendToBack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SendToBackIcon, color, size)
