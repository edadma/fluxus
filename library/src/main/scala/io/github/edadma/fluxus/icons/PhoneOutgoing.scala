package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/phone-outgoing", JSImport.Default)
private object PhoneOutgoingIcon extends js.Array[js.Any]

def PhoneOutgoing(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PhoneOutgoingIcon, color, size)
