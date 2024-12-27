package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/save", JSImport.Default)
private object SaveIcon extends js.Array[js.Any]

def Save(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SaveIcon, color, size)
