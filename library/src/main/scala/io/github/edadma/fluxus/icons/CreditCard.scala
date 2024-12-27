package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/credit-card", JSImport.Default)
private object CreditCardIcon extends js.Array[js.Any]

def CreditCard(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CreditCardIcon, color, size)
