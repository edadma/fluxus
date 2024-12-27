package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-warning", JSImport.Default)
private object MailWarningIcon extends js.Array[js.Any]

def MailWarning(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailWarningIcon, color, size)
