package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/forklift", JSImport.Default)
private object ForkliftIcon extends js.Array[js.Any]

def Forklift(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ForkliftIcon, color, size)
