package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tickets", JSImport.Default)
private object TicketsIcon extends js.Array[js.Any]

def Tickets(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketsIcon, color, size)
