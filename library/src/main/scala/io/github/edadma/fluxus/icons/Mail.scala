package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail", JSImport.Default)
private object MailIcon extends js.Array[js.Any]

def Mail(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailIcon, color, size)
