package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dice-5", JSImport.Default)
private object Dice5Icon extends js.Array[js.Any]

def Dice5(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Dice5Icon, color, size)
