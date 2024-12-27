package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bitcoin", JSImport.Default)
private object BitcoinIcon extends js.Array[js.Any]

def Bitcoin(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BitcoinIcon, color, size)
