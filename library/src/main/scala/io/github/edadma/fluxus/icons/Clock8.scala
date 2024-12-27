package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-8", JSImport.Default)
private object Clock8Icon extends js.Array[js.Any]

def Clock8(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock8Icon, color, size)
