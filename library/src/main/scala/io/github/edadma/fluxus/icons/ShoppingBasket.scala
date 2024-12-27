package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shopping-basket", JSImport.Default)
private object ShoppingBasketIcon extends js.Array[js.Any]

def ShoppingBasket(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShoppingBasketIcon, color, size)
