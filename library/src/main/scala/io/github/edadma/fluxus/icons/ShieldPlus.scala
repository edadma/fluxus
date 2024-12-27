package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-plus", JSImport.Default)
private object ShieldPlusIcon extends js.Array[js.Any]

def ShieldPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldPlusIcon, color, size)
