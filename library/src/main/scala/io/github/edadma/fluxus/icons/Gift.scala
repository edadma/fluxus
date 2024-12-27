package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gift", JSImport.Default)
private object GiftIcon extends js.Array[js.Any]

def Gift(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GiftIcon, color, size)
