package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-7", JSImport.Default)
private object Clock7Icon extends js.Array[js.Any]

def Clock7(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock7Icon, color, size)
