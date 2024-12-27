package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trash", JSImport.Default)
private object TrashIcon extends js.Array[js.Any]

def Trash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrashIcon, color, size)
