package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/copyleft", JSImport.Default)
private object CopyleftIcon extends js.Array[js.Any]

def Copyleft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CopyleftIcon, color, size)
