package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shopping-bag", JSImport.Default)
private object ShoppingBagIcon extends js.Array[js.Any]

def ShoppingBag(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShoppingBagIcon, color, size)
