package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-11", JSImport.Default)
private object Clock11Icon extends js.Array[js.Any]

def Clock11(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock11Icon, color, size)
