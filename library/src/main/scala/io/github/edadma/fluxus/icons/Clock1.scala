package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-1", JSImport.Default)
private object Clock1Icon extends js.Array[js.Any]

def Clock1(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock1Icon, color, size)
