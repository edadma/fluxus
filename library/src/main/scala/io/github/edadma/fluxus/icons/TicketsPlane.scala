package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tickets-plane", JSImport.Default)
private object TicketsPlaneIcon extends js.Array[js.Any]

def TicketsPlane(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketsPlaneIcon, color, size)
