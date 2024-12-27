package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/copy-x", JSImport.Default)
private object CopyXIcon extends js.Array[js.Any]

def CopyX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CopyXIcon, color, size)
