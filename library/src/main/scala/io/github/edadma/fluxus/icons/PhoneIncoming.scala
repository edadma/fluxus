package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/phone-incoming", JSImport.Default)
private object PhoneIncomingIcon extends js.Array[js.Any]

def PhoneIncoming(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PhoneIncomingIcon, color, size)
