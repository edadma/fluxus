package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dice-2", JSImport.Default)
private object Dice2Icon extends js.Array[js.Any]

def Dice2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Dice2Icon, color, size)
