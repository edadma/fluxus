package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pencil", JSImport.Default)
private object PencilIcon extends js.Array[js.Any]

def Pencil(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PencilIcon, color, size)
