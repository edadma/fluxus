package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/store", JSImport.Default)
private object StoreIcon extends js.Array[js.Any]

def Store(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StoreIcon, color, size)
