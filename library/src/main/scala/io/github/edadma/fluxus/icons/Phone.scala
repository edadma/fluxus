package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/phone", JSImport.Default)
private object PhoneIcon extends js.Array[js.Any]

def Phone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PhoneIcon, color, size)
