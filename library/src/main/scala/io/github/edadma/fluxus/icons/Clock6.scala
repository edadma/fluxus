package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-6", JSImport.Default)
private object Clock6Icon extends js.Array[js.Any]

def Clock6(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock6Icon, color, size)
