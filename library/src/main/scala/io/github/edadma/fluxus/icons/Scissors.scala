package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scissors", JSImport.Default)
private object ScissorsIcon extends js.Array[js.Any]

def Scissors(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScissorsIcon, color, size)
