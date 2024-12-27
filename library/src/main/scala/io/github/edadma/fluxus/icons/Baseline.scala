package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/baseline", JSImport.Default)
private object BaselineIcon extends js.Array[js.Any]

def Baseline(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BaselineIcon, color, size)
