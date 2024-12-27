package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/copy", JSImport.Default)
private object CopyIcon extends js.Array[js.Any]

def Copy(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CopyIcon, color, size)
