package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ticket-percent", JSImport.Default)
private object TicketPercentIcon extends js.Array[js.Any]

def TicketPercent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketPercentIcon, color, size)
