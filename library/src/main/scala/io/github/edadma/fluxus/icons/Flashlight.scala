package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flashlight", JSImport.Default)
private object FlashlightIcon extends js.Array[js.Any]

def Flashlight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlashlightIcon, color, size)
