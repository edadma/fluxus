package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shopping-cart", JSImport.Default)
private object ShoppingCartIcon extends js.Array[js.Any]

def ShoppingCart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShoppingCartIcon, color, size)
