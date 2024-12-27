package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-plus", JSImport.Default)
private object ListPlusIcon extends js.Array[js.Any]

def ListPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListPlusIcon, color, size)
