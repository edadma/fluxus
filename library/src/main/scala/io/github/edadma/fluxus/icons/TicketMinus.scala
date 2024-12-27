package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ticket-minus", JSImport.Default)
private object TicketMinusIcon extends js.Array[js.Any]

def TicketMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TicketMinusIcon, color, size)
