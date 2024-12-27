package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hash", JSImport.Default)
private object HashIcon extends js.Array[js.Any]

def Hash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HashIcon, color, size)
