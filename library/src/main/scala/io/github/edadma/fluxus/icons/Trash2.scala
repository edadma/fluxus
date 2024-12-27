package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trash-2", JSImport.Default)
private object Trash2Icon extends js.Array[js.Any]

def Trash2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Trash2Icon, color, size)
