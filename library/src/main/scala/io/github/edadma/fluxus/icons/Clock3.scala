package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-3", JSImport.Default)
private object Clock3Icon extends js.Array[js.Any]

def Clock3(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock3Icon, color, size)
