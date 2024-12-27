package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ticket", JSImport.Default)
private object TicketIcon extends js.Array[js.Any]

def Ticket(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketIcon, color, size)
