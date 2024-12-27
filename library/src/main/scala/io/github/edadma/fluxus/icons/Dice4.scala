package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dice-4", JSImport.Default)
private object Dice4Icon extends js.Array[js.Any]

def Dice4(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Dice4Icon, color, size)
