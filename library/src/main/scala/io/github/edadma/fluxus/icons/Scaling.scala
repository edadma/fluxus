package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scaling", JSImport.Default)
private object ScalingIcon extends js.Array[js.Any]

def Scaling(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScalingIcon, color, size)
