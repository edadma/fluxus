package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/party-popper", JSImport.Default)
private object PartyPopperIcon extends js.Array[js.Any]

def PartyPopper(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PartyPopperIcon, color, size)
