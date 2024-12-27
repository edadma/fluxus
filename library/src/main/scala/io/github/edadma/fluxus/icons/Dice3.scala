package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dice-3", JSImport.Default)
private object Dice3Icon extends js.Array[js.Any]

def Dice3(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Dice3Icon, color, size)
