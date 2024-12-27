package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/step-back", JSImport.Default)
private object StepBackIcon extends js.Array[js.Any]

def StepBack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StepBackIcon, color, size)
