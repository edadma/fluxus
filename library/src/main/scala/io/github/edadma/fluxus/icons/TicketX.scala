package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ticket-x", JSImport.Default)
private object TicketXIcon extends js.Array[js.Any]

def TicketX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketXIcon, color, size)
