package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/piggy-bank", JSImport.Default)
private object PiggyBankIcon extends js.Array[js.Any]

def PiggyBank(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PiggyBankIcon, color, size)
