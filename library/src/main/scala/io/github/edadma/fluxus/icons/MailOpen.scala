package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-open", JSImport.Default)
private object MailOpenIcon extends js.Array[js.Any]

def MailOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailOpenIcon, color, size)
