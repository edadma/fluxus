package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sword", JSImport.Default)
private object SwordIcon extends js.Array[js.Any]

def Sword(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SwordIcon, color, size)
