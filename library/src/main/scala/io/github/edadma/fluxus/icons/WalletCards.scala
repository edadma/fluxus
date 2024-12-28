package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wallet-cards", JSImport.Default)
private object WalletCardsIcon extends js.Array[js.Any]

def WalletCards(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WalletCardsIcon, color, size)