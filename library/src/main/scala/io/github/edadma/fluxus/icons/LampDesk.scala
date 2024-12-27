package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lamp-desk", JSImport.Default)
private object LampDeskIcon extends js.Array[js.Any]

def LampDesk(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LampDeskIcon, color, size)
