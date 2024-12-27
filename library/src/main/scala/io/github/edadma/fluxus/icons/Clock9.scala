package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-9", JSImport.Default)
private object Clock9Icon extends js.Array[js.Any]

def Clock9(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock9Icon, color, size)
