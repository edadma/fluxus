package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ticket-plus", JSImport.Default)
private object TicketPlusIcon extends js.Array[js.Any]

def TicketPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketPlusIcon, color, size)
