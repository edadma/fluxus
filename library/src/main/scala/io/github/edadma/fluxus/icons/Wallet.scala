package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wallet", JSImport.Default)
private object WalletIcon extends js.Array[js.Any]

def Wallet(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WalletIcon, color, size)
