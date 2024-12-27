package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mails", JSImport.Default)
private object MailsIcon extends js.Array[js.Any]

def Mails(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailsIcon, color, size)
