package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-4", JSImport.Default)
private object Clock4Icon extends js.Array[js.Any]

def Clock4(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Clock4Icon, color, size)
