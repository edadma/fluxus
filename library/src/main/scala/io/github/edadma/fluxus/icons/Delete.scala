package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/delete", JSImport.Default)
private object DeleteIcon extends js.Array[js.Any]

def Delete(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DeleteIcon, color, size)
