package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/biohazard", JSImport.Default)
private object BiohazardIcon extends js.Array[js.Any]

def Biohazard(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BiohazardIcon, color, size)
