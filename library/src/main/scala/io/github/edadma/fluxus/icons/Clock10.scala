package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-10", JSImport.Default)
private object Clock10Icon extends js.Array[js.Any]

def Clock10(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock10Icon, color, size)
