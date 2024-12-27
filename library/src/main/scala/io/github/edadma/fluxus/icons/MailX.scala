package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-x", JSImport.Default)
private object MailXIcon extends js.Array[js.Any]

def MailX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailXIcon, color, size)
