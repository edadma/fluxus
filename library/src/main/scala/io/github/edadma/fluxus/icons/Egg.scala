package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/egg", JSImport.Default)
private object EggIcon extends js.Array[js.Any]

def Egg(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EggIcon, color, size)
