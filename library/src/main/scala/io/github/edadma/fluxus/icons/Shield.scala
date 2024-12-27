package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield", JSImport.Default)
private object ShieldIcon extends js.Array[js.Any]

def Shield(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldIcon, color, size)
