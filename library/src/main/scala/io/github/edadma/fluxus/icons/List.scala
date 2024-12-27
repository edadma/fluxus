package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list", JSImport.Default)
private object ListIcon extends js.Array[js.Any]

def List(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListIcon, color, size)
