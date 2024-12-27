package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/a-large-small", JSImport.Default)
private object ALargeSmallIcon extends js.Array[js.Any]

def ALargeSmall(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ALargeSmallIcon, color, size)
