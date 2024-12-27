package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/candy-cane", JSImport.Default)
private object CandyCaneIcon extends js.Array[js.Any]

def CandyCane(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CandyCaneIcon, color, size)
