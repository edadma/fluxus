package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/contact", JSImport.Default)
private object ContactIcon extends js.Array[js.Any]

def Contact(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ContactIcon, color, size)
