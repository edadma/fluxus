package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wallet-minimal", JSImport.Default)
private object WalletMinimalIcon extends js.Array[js.Any]

def WalletMinimal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WalletMinimalIcon, color, size)
