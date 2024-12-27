package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/phone-call", JSImport.Default)
private object PhoneCallIcon extends js.Array[js.Any]

def PhoneCall(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PhoneCallIcon, color, size)
