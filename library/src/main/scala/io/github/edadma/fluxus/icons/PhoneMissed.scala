package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/phone-missed", JSImport.Default)
private object PhoneMissedIcon extends js.Array[js.Any]

def PhoneMissed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PhoneMissedIcon, color, size)
