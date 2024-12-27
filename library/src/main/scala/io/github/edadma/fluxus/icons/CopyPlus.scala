package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/copy-plus", JSImport.Default)
private object CopyPlusIcon extends js.Array[js.Any]

def CopyPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CopyPlusIcon, color, size)
