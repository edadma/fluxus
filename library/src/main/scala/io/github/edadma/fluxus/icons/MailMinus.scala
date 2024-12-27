package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-minus", JSImport.Default)
private object MailMinusIcon extends js.Array[js.Any]

def MailMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailMinusIcon, color, size)
