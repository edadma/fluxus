package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/anvil", JSImport.Default)
private object AnvilIcon extends js.Array[js.Any]

def Anvil(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AnvilIcon, color, size)
