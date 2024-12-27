package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cross", JSImport.Default)
private object CrossIcon extends js.Array[js.Any]

def Cross(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CrossIcon, color, size)
