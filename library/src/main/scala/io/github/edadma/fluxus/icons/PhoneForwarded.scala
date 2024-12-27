package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/phone-forwarded", JSImport.Default)
private object PhoneForwardedIcon extends js.Array[js.Any]

def PhoneForwarded(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PhoneForwardedIcon, color, size)
