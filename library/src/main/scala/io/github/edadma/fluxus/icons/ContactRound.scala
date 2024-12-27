package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/contact-round", JSImport.Default)
private object ContactRoundIcon extends js.Array[js.Any]

def ContactRound(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ContactRoundIcon, color, size)
