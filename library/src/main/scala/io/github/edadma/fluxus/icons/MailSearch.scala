package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-search", JSImport.Default)
private object MailSearchIcon extends js.Array[js.Any]

def MailSearch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailSearchIcon, color, size)
