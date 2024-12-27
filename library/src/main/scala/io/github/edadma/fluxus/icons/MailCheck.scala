package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-check", JSImport.Default)
private object MailCheckIcon extends js.Array[js.Any]

def MailCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailCheckIcon, color, size)
