package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-plus", JSImport.Default)
private object MailPlusIcon extends js.Array[js.Any]

def MailPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailPlusIcon, color, size)
