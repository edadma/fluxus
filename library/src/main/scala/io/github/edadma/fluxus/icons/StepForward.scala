package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/step-forward", JSImport.Default)
private object StepForwardIcon extends js.Array[js.Any]

def StepForward(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StepForwardIcon, color, size)
