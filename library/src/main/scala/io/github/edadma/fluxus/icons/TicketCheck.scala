package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ticket-check", JSImport.Default)
private object TicketCheckIcon extends js.Array[js.Any]

def TicketCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketCheckIcon, color, size)
