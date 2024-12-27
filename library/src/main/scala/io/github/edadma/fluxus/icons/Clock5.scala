package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-5", JSImport.Default)
private object Clock5Icon extends js.Array[js.Any]

def Clock5(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock5Icon, color, size)
