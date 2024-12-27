package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dice-6", JSImport.Default)
private object Dice6Icon extends js.Array[js.Any]

def Dice6(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Dice6Icon, color, size)
